package com.arjunjadeja.texty.internal.styles.list

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.ScrollingDirection
import kotlinx.datetime.Clock
import kotlin.math.roundToInt

@Composable
internal fun ScrollingList(
    textList: List<String>,
    spacing: Dp,
    scrollingDirection: ScrollingDirection,
    scrollDuration: Long,
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

    LaunchedEffect(scrollingDirection, repeat) {
        when (repeat) {
            Repeat.Once -> {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(scrollDuration.toInt(), easing = LinearEasing)
                )
                onComplete()
            }

            Repeat.Continuous -> {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(scrollDuration.toInt(), easing = LinearEasing),
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
                        animationSpec = tween(scrollDuration.toInt(), easing = LinearEasing)
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
                        animationSpec = tween(scrollDuration.toInt(), easing = LinearEasing)
                    )
                    repeatCount++
                    onComplete()
                }
                if (repeat.showAfterComplete) showFinalPosition = true
            }
        }
    }

    val scrollModifier = modifier
        .clipToBounds()
        .layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, constraints.maxHeight) {
            val yOffset = if (showFinalPosition) {
                when (scrollingDirection) {
                    ScrollingDirection.TowardsBottom -> constraints.maxHeight - placeable.height
                    ScrollingDirection.TowardsTop -> 0
                }
            } else {
                when (scrollingDirection) {
                    ScrollingDirection.TowardsBottom -> {
                        ((progress.value * (constraints.maxHeight + placeable.height)) - placeable.height).roundToInt()
                    }

                    ScrollingDirection.TowardsTop -> {
                        (((1f - progress.value) * (constraints.maxHeight + placeable.height)) - placeable.height).roundToInt()
                    }
                }
            }
            placeable.place(IntOffset(x = 0, y = yOffset))
        }
    }

    Column(
        modifier = scrollModifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        textList.forEach { text ->
            BasicText(
                text = text,
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

