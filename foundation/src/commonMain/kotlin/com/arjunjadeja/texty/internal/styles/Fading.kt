package com.arjunjadeja.texty.internal.styles

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.FadingType

@Composable
internal fun Fading(
    text: String,
    type: FadingType,
    durationInMillis: Long,
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
    var alpha by remember { mutableFloatStateOf(if (type == FadingType.IN) 0f else 1f) }
    LaunchedEffect(type) {
        val startTime = withFrameNanos { it }
        while (true) {
            withFrameNanos { currentTime ->
                val elapsedTime = (currentTime - startTime) / 1_000_000
                val progress = (elapsedTime.toFloat() / durationInMillis).coerceIn(0f, 1f)
                alpha = when (type) {
                    FadingType.IN -> progress
                    FadingType.OUT -> 1f - progress
                }
                if (elapsedTime >= durationInMillis) {
                    onComplete()
                    return@withFrameNanos
                }
            }
        }
    }
    BasicText(
        text = text,
        modifier = modifier.alpha(alpha),
        style = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color
    )
}