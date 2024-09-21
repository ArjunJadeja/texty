package com.arjunjadeja.texty.internal.styles.utility

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
