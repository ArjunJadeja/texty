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
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.SlidingDirection
import kotlinx.datetime.Clock
import kotlin.math.roundToInt

@Composable
internal fun Sliding(
    text: String,
    slidingDirection: SlidingDirection,
    slideDuration: Long,
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

    LaunchedEffect(slidingDirection, repeat) {
        when (repeat) {
            Repeat.Once -> {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(slideDuration.toInt(), easing = LinearEasing)
                )
                onComplete()
            }

            Repeat.Continuous -> {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(slideDuration.toInt(), easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    )
                )
                onComplete()
            }

            is Repeat.TimeBound -> {
                val endTime = Clock.System.now().toEpochMilliseconds() + repeat.durationInMillis
                do {
                    progress.snapTo(targetValue = 0f)
                    progress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(slideDuration.toInt(), easing = LinearEasing)
                    )
                    onComplete()
                } while (Clock.System.now().toEpochMilliseconds() < endTime)
                if (repeat.showAfterComplete) showFinalPosition = true
            }

            is Repeat.CountBound -> {
                var repeatCount = 0
                while (repeatCount < repeat.repeatCount) {
                    progress.snapTo(targetValue = 0f)
                    progress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(slideDuration.toInt(), easing = LinearEasing)
                    )
                    repeatCount++
                    onComplete()
                }
                if (repeat.showAfterComplete) showFinalPosition = true
            }
        }
    }

    val slideModifier = modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(constraints.maxWidth, placeable.height) {
            val xOffset = if (showFinalPosition) {
                when (slidingDirection) {
                    SlidingDirection.TowardsEnd -> constraints.maxWidth - placeable.width
                    SlidingDirection.TowardsStart -> 0
                }
            } else {
                when (slidingDirection) {
                    SlidingDirection.TowardsEnd -> {
                        ((progress.value * (constraints.maxWidth + placeable.width)) - placeable.width).roundToInt()
                    }

                    SlidingDirection.TowardsStart -> {
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