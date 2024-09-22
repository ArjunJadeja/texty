package com.arjunjadeja.texty.internal.styles.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.internal.styles.normal.Sliding

@Composable
internal fun SlidingList(
    textList: List<String>,
    separator: String?,
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
) = Sliding(
    text = textList.joinToString(separator = separator ?: " "),
    slidingDirection = slidingDirection,
    slideDuration = slideDuration,
    repeat = repeat,
    modifier = modifier,
    textStyle = textStyle,
    onTextLayout = onTextLayout,
    overflow = overflow,
    softWrap = softWrap,
    maxLines = maxLines,
    minLines = minLines,
    color = color,
    onComplete = onComplete
)
