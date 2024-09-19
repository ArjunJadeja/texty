package com.arjunjadeja.texty

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.internal.styles.Basic
import com.arjunjadeja.texty.internal.styles.Blinking
import com.arjunjadeja.texty.internal.styles.Fading
import com.arjunjadeja.texty.internal.styles.Typing

@Composable
fun Texty(
    text: String,
    displayStyle: DisplayStyle = DisplayStyle.Basic(),
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null
) = when (displayStyle) {
    is DisplayStyle.Basic -> Basic(
        text = text,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onTextDisplayed = displayStyle.onTextDisplayed
    )

    is DisplayStyle.Typing -> Typing(
        text = text,
        typingDelayPerChar = displayStyle.typingDelayPerChar,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onTextDisplayed = displayStyle.onTextDisplayed
    )

    is DisplayStyle.Blinking -> Blinking(
        text = text,
        blinkInterval = displayStyle.blinkInterval,
        repeat = displayStyle.repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onBlink = displayStyle.onBlink
    )

    is DisplayStyle.Fading -> Fading(
        text = text,
        type = displayStyle.type,
        durationInMillis = displayStyle.durationInMillis,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )
}
