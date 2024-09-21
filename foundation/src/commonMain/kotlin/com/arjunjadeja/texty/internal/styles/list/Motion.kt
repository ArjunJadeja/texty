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

@Composable
internal fun Motion(
    frames: List<String>,
    frameDisplayDelay: Long,
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
                for (i in frames.indices) {
                    currentFrame = frames[i]
                    delay(frameDisplayDelay)
                }
                onComplete()
            }

            Repeat.Continuous -> {
                while (true) {
                    for (i in frames.indices) {
                        currentFrame = frames[i]
                        delay(frameDisplayDelay)
                    }
                }
            }

            is Repeat.TimeBound -> {
                val endTime = Clock.System.now().toEpochMilliseconds() + repeat.durationInMillis
                while (Clock.System.now().toEpochMilliseconds() < endTime) {
                    for (i in frames.indices) {
                        currentFrame = frames[i]
                        delay(frameDisplayDelay)
                        if (Clock.System.now().toEpochMilliseconds() >= endTime) break
                    }
                }
                currentFrame = if (repeat.showAfterComplete) frames.last() else ""
                onComplete()
            }

            is Repeat.CountBound -> {
                var repeatCount = 0
                while (repeatCount < repeat.repeatCount) {
                    for (i in frames.indices) {
                        currentFrame = frames[i]
                        delay(frameDisplayDelay)
                    }
                    repeatCount++
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