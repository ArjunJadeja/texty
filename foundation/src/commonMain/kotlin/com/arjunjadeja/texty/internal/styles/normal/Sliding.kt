/*
 * Copyright (C) 2024 Arjun Jadeja (arjunjadeja.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arjunjadeja.texty.internal.styles.normal

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.SlidingDirection
import kotlinx.datetime.Clock
import kotlin.math.roundToInt

/**
 * A composable function that slides text horizontally in the specified direction and duration.
 *
 * @param text The text to slide.
 * @param direction The direction of sliding (left or right).
 * @param duration The duration for a complete slide cycle.
 * @param repeat Defines how the sliding should repeat.
 * @param modifier The modifier to be applied to the text.
 * @param textStyle Style configuration for the text.
 * @param onTextLayout Optional callback for text layout information.
 * @param overflow How text overflow should be handled.
 * @param softWrap Whether text should wrap at soft line breaks.
 * @param maxLines The maximum number of lines to display.
 * @param minLines The minimum number of lines to display.
 * @param color Optional producer for text color.
 * @param onComplete A callback triggered when the animation completes.
 */
@Composable
internal fun Sliding(
    text: String,
    direction: SlidingDirection,
    duration: Long,
    repeat: Repeat,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null,
    onComplete: () -> Unit = {}
) {
    val progress = remember { Animatable(initialValue = 0f) }
    var showFinalPosition by remember { mutableStateOf(false) }

    LaunchedEffect(direction, repeat) {
        when (repeat) {
            Repeat.Once -> {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(duration.toInt(), easing = LinearEasing)
                )
                onComplete()
            }

            Repeat.Continuous -> {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(duration.toInt(), easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
                onComplete()
            }

            is Repeat.TimeBound -> {
                val endTime = Clock.System.now().toEpochMilliseconds() + repeat.duration
                do {
                    progress.snapTo(targetValue = 0f)
                    progress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(duration.toInt(), easing = LinearEasing)
                    )
                    onComplete()
                } while (Clock.System.now().toEpochMilliseconds() < endTime)
                if (repeat.showAfterComplete) showFinalPosition = true
            }

            is Repeat.CountBound -> {
                var repeatCount = 0
                while (repeatCount < repeat.count) {
                    progress.snapTo(targetValue = 0f)
                    progress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(duration.toInt(), easing = LinearEasing)
                    )
                    repeatCount++
                    onComplete()
                }
                if (repeat.showAfterComplete) showFinalPosition = true
            }
        }
    }

    val slideModifier = modifier
        .clipToBounds()
        .layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)
            layout(constraints.maxWidth, placeable.height) {
                val xOffset = if (showFinalPosition) {
                    when (direction) {
                        SlidingDirection.TOWARDS_END -> constraints.maxWidth - placeable.width
                        SlidingDirection.TOWARDS_START -> 0
                    }
                } else {
                    when (direction) {
                        SlidingDirection.TOWARDS_END -> {
                            ((progress.value * (constraints.maxWidth + placeable.width)) - placeable.width).roundToInt()
                        }

                        SlidingDirection.TOWARDS_START -> {
                            (((1f - progress.value) * (constraints.maxWidth + placeable.width)) - placeable.width).roundToInt()
                        }
                    }
                }
                placeable.place(x = xOffset, y = 0)
            }
        }

    BasicText(
        text = text,
        modifier = slideModifier,
        style = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color
    )
}