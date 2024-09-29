/*
 * Copyright (C) 2024 Arjun Jadeja (arjunjadeja.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

/**
 * Displays text that reveals itself character by character based on the specified pattern and type.
 *
 * @param text The text to display.
 * @param cover The cover pattern used initially (default or custom).
 * @param pattern The revealing pattern (start to end, end to start, etc.).
 * @param type The type of revealing (by each character or total time).
 * @param delayBeforeRevealing Delay before the revealing starts.
 * @param modifier Modifier for the text composable.
 * @param textStyle Style to apply to the text.
 * @param onTextLayout Callback when text layout is complete.
 * @param overflow How to handle text overflow.
 * @param softWrap Whether to wrap text at soft breaks.
 * @param maxLines Maximum number of lines to display.
 * @param minLines Minimum number of lines to display.
 * @param color Optional color producer for text color.
 * @param onComplete Callback triggered when the revealing is complete.
 */
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

/**
 * Repeats the string to fill the specified length.
 *
 * @param length The total length to cover.
 * @return A string repeated to cover the given length.
 */
private fun String.getFullCover(length: Int) = this.repeat(length)