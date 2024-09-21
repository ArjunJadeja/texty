package com.arjunjadeja.texty.internal.styles.normal

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
import kotlinx.coroutines.delay

@Composable
internal fun Typing(
    text: String,
    typingDelayPerChar: Long,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null,
    onTextDisplayed: () -> Unit = {}
) {
    var displayedText by remember { mutableStateOf("") }
    LaunchedEffect(text) {
        val stringBuilder = StringBuilder()
        text.forEach {
            stringBuilder.append(it)
            displayedText = stringBuilder.toString()
            delay(typingDelayPerChar)
        }
        delay(typingDelayPerChar)
        onTextDisplayed()
    }
    BasicText(
        text = displayedText,
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