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
import com.arjunjadeja.texty.RevealingCover
import com.arjunjadeja.texty.RevealingPattern
import com.arjunjadeja.texty.RevealingType
import kotlinx.coroutines.delay

@Composable
internal fun Revealing(
    text: String,
    cover: RevealingCover,
    pattern: RevealingPattern,
    type: RevealingType,
    delayBeforeRevealing: Long,
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
    val coverText = when (cover) {
        is RevealingCover.Default -> "⣿".getFullCover(text.length)
        is RevealingCover.Custom -> {
            when (cover.cover.length) {
                1 -> cover.cover.getFullCover(text.length)
                text.length -> cover.cover
                else -> cover.cover
                    .firstOrNull()
                    ?.takeIf { it != ' ' }
                    ?.toString()
                    ?.getFullCover(text.length)
                    ?: "⣿".getFullCover(text.length)
            }
        }
    }

    var displayedText by remember { mutableStateOf(coverText) }
    val totalCharacters = text.length

    val calculatedDelay = when (type) {
        is RevealingType.ByEachCharacter -> type.delayInMillis
        is RevealingType.ByTotalTime -> type.durationInMillis / totalCharacters
    }

    LaunchedEffect(text, pattern, type) {
        delay(delayBeforeRevealing)

        when (pattern) {
            RevealingPattern.START_TO_END -> {
                for (i in text.indices) {
                    delay(calculatedDelay)
                    displayedText = displayedText.replaceRange(i..i, text[i].toString())
                }
            }

            RevealingPattern.END_TO_START -> {
                for (i in text.indices.reversed()) {
                    delay(calculatedDelay)
                    displayedText = displayedText.replaceRange(i..i, text[i].toString())
                }
            }

            RevealingPattern.CENTER_TO_SIDES -> {
                val center = text.length / 2
                for (i in 0..center) {
                    delay(calculatedDelay)
                    if (center - i >= 0) {
                        displayedText = displayedText.replaceRange(
                            center - i..center - i,
                            text[center - i].toString()
                        )
                    }
                    if (center + i < text.length) {
                        displayedText = displayedText.replaceRange(
                            center + i..center + i,
                            text[center + i].toString()
                        )
                    }
                }
            }

            RevealingPattern.SIDES_TO_CENTER -> {
                val center = text.length / 2
                for (i in 0..center) {
                    delay(calculatedDelay)
                    if (i < text.length / 2) {
                        displayedText = displayedText.replaceRange(i..i, text[i].toString())
                        displayedText = displayedText.replaceRange(
                            text.length - 1 - i..text.length - 1 - i,
                            text[text.length - 1 - i].toString()
                        )
                    }
                }
            }
        }
        onComplete()
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

private fun String.getFullCover(length: Int) = this.repeat(length)