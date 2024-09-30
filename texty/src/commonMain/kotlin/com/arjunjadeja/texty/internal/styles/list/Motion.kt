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

package com.arjunjadeja.texty.internal.styles.list

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.Repeat
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock

/**
 * Displays a list of text frames in motion, switching between them at specified intervals.
 *
 * @param frames List of strings to be displayed one after the other.
 * @param delayBeforeNext The delay in milliseconds before showing the next frame.
 * @param repeat Specifies how the frames should repeat: Once, Continuously, TimeBound, or CountBound.
 * @param modifier The modifier to be applied to the text.
 * @param textStyle Style configuration for the text.
 * @param onTextLayout Optional callback for text layout information.
 * @param overflow How text overflow should be handled.
 * @param softWrap Whether text should wrap at soft line breaks.
 * @param maxLines The maximum number of lines to display.
 * @param minLines The minimum number of lines to display.
 * @param color Optional producer for text color.
 * @param onComplete A callback triggered when the motion sequence completes.
 */
@Composable
internal fun Motion(
    frames: List<String>,
    delayBeforeNext: Long,
    repeat: Repeat,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null,
    onComplete: () -> Unit = {},
) {
    var currentFrame by remember { mutableStateOf(frames.first()) }

    LaunchedEffect(frames, repeat) {
        when (repeat) {
            Repeat.Once -> {
                frames.forEach { frame ->
                    currentFrame = frame
                    delay(delayBeforeNext)
                }
                onComplete()
            }

            Repeat.Continuous -> {
                while (true) {
                    frames.forEach { frame ->
                        currentFrame = frame
                        delay(delayBeforeNext)
                    }
                }
            }

            is Repeat.TimeBound -> {
                val endTime = Clock.System.now().toEpochMilliseconds() + repeat.duration
                while (Clock.System.now().toEpochMilliseconds() < endTime) {
                    frames.forEach { frame ->
                        currentFrame = frame
                        delay(delayBeforeNext)
                        if (Clock.System.now()
                                .toEpochMilliseconds() >= endTime
                        ) return@LaunchedEffect
                    }
                }
                currentFrame = if (repeat.showAfterComplete) frames.last() else ""
                onComplete()
            }

            is Repeat.CountBound -> {
                repeat(repeat.count) {
                    frames.forEach { frame ->
                        currentFrame = frame
                        delay(delayBeforeNext)
                    }
                }
                currentFrame = if (repeat.showAfterComplete) frames.last() else ""
                onComplete()
            }
        }
    }

    BasicText(
        text = currentFrame,
        modifier = modifier,
        style = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color
    )
}