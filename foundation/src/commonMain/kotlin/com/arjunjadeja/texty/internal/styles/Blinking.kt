package com.arjunjadeja.texty.internal.styles

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
internal fun Blinking(
    text: String,
    blinkInterval: Long,
    repeat: Repeat,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null,
    onBlink: () -> Unit = {}
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        when (repeat) {
            Repeat.Once -> {
                delay(timeMillis = blinkInterval / 2)
                isVisible = !isVisible
                if (isVisible) onBlink()
            }

            Repeat.Infinite -> {
                do {
                    delay(timeMillis = blinkInterval / 2)
                    isVisible = !isVisible
                    if (isVisible) onBlink()
                } while (true)
            }

            is Repeat.TimeBound -> {
                val endTime = Clock.System.now().toEpochMilliseconds() + repeat.durationInMillis
                do {
                    delay(timeMillis = blinkInterval / 2)
                    isVisible = !isVisible
                    if (isVisible) onBlink()
                } while (Clock.System.now().toEpochMilliseconds() < endTime)
                isVisible = repeat.showAfterComplete
            }

            is Repeat.CountBound -> {
                var repeatCount = 0
                while (repeatCount < repeat.repeatCount) {
                    delay(timeMillis = blinkInterval / 2)
                    isVisible = !isVisible
                    if (isVisible) {
                        repeatCount++
                        onBlink()
                    }
                }
                isVisible = repeat.showAfterComplete
            }
        }
    }
    if (isVisible) {
        BasicText(
            text = text,
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