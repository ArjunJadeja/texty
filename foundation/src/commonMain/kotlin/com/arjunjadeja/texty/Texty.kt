package com.arjunjadeja.texty

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.internal.styles.list.Motion
import com.arjunjadeja.texty.internal.styles.normal.Basic
import com.arjunjadeja.texty.internal.styles.normal.Blinking
import com.arjunjadeja.texty.internal.styles.normal.Fading
import com.arjunjadeja.texty.internal.styles.normal.Revealing
import com.arjunjadeja.texty.internal.styles.normal.Typing
import com.arjunjadeja.texty.internal.styles.utility.Loading
import com.arjunjadeja.texty.internal.styles.utility.TimeKeeping

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

    is DisplayStyle.Revealing -> Revealing(
        text = text,
        delayBeforeRevealing = displayStyle.delayBeforeRevealing,
        pattern = displayStyle.pattern,
        type = displayStyle.type,
        cover = displayStyle.cover,
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

@Composable
fun Texty(
    textList: List<String>,
    displayStyle: ListDisplayStyle,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null
) = when (displayStyle) {
    is ListDisplayStyle.Motion -> Motion(
        frames = textList,
        frameDisplayDelay = displayStyle.frameDisplayDelay,
        repeat = displayStyle.repeat,
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

@Composable
fun Texty(
    utility: Utility,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null
) = when (utility) {
    is Utility.Loading -> Loading(
        loadingType = utility.type,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color
    )

    is Utility.TimeKeeping -> TimeKeeping(
        format = utility.format,
        liveUpdate = utility.liveUpdate,
        updateInterval = utility.updateInterval,
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
