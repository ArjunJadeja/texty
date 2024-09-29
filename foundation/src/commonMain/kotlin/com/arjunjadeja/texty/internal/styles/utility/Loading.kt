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

package com.arjunjadeja.texty.internal.styles.utility

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arjunjadeja.texty.LoadingType

/**
 * Displays different types of loading styles based on the specified [loadingType].
 *
 * @param loadingType The type of loading to be displayed.
 * @param modifier The modifier to be applied to the composable.
 * @param textStyle The style to be applied to the text.
 * @param onTextLayout Callback to be invoked when text layout changes.
 * @param overflow How to handle text that is too long to fit.
 * @param softWrap Whether the text is soft-wrapped.
 * @param maxLines The maximum number of lines allowed for the text.
 * @param minLines The minimum number of lines required for the text.
 * @param color Optional color producer for the text.
 */
@Composable
internal fun Loading(
    loadingType: LoadingType,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null,
) = when (loadingType) {
    is LoadingType.Spinner -> {
        val spinnerSteps = listOf("⠋", "⠙", "⠹", "⠸", "⠼", "⠴", "⠦", "⠧", "⠇", "⠏")
        AnimatedIndicator(
            steps = spinnerSteps,
            cycleDuration = loadingType.cycleDurationInMillis,
            modifier = modifier,
            textStyle = textStyle,
            onTextLayout = onTextLayout,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            color = color
        )
    }

    is LoadingType.Circular -> {
        val circularSteps = listOf("◐", "◓", "◑", "◒")
        AnimatedIndicator(
            steps = circularSteps,
            cycleDuration = loadingType.cycleDurationInMillis,
            modifier = modifier,
            textStyle = textStyle,
            onTextLayout = onTextLayout,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            color = color
        )
    }

    is LoadingType.MusicBar -> {
        val barSteps = listOf("▁", "▂", "▃", "▄", "▅", "▆", "▇", "█")
        MusicBarIndicator(
            barSteps = barSteps,
            cycleDuration = loadingType.cycleDurationInMillis,
            barCount = loadingType.barCount,
            modifier = modifier,
            textStyle = textStyle,
            onTextLayout = onTextLayout,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            color = color
        )
    }
}

/**
 * Displays an animated indicator based on a list of [steps].
 *
 * @param steps The list of characters used for animation.
 * @param cycleDuration The duration of the animation cycle.
 */
@Composable
private fun AnimatedIndicator(
    steps: List<String>,
    cycleDuration: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null,
) {
    val animationProgress = animateLoadingStep(steps.size, cycleDuration)
    val currentIndex = animationProgress.toInt() % steps.size
    val nextIndex = (currentIndex + 1) % steps.size
    val progress = animationProgress - animationProgress.toInt()

    Box(modifier = modifier) {
        BasicText(
            text = steps[currentIndex],
            modifier = Modifier.alpha(1f - progress),
            style = textStyle,
            onTextLayout = onTextLayout,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            color = color
        )
        BasicText(
            text = steps[nextIndex],
            modifier = Modifier.alpha(progress),
            style = textStyle,
            onTextLayout = onTextLayout,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            color = color
        )
    }
}

/**
 * Creates an infinite animation for cycling through steps based on [stepCount] and [cycleDuration].
 *
 * @param stepCount The number of steps in the animation.
 * @param cycleDuration The total duration of one animation cycle in milliseconds.
 * @return The current progress of the animation as a float value.
 */
@Composable
private fun animateLoadingStep(stepCount: Int, cycleDuration: Int): Float {
    val infiniteTransition = rememberInfiniteTransition()
    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = stepCount.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(cycleDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    return animationProgress
}

/**
 * Displays an animated bar indicator with a music-like effect using [barSteps].
 *
 * @param barSteps The list of characters representing different bar heights.
 * @param cycleDuration The duration of the animation cycle in milliseconds.
 * @param barCount The number of bars to display.
 */
@Composable
private fun MusicBarIndicator(
    barSteps: List<String>,
    cycleDuration: Int,
    barCount: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null,
) {
    val infiniteTransition = rememberInfiniteTransition()

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        for (i in 0 until barCount) {
            val delay = (i * cycleDuration / barCount).toLong()
            val animationProgress by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = barSteps.size.toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        cycleDuration,
                        easing = LinearEasing,
                        delayMillis = delay.toInt()
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
            val currentIndex = animationProgress.toInt() % barSteps.size
            BasicText(
                text = barSteps[currentIndex],
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
    }
}