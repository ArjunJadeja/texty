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
import com.arjunjadeja.texty.Repeat
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock

/**
 * Displays blinking text based on the specified interval and repeat pattern.
 *
 * @param text The text to display.
 * @param interval Time interval for the blinking effect.
 * @param repeat The repeat pattern for the blinking effect.
 * @param modifier Modifier for the text composable.
 * @param textStyle Style to apply to the text.
 * @param onTextLayout Callback when text layout is complete.
 * @param overflow How to handle text overflow.
 * @param softWrap Whether to wrap text at soft breaks.
 * @param maxLines Maximum number of lines to display.
 * @param minLines Minimum number of lines to display.
 * @param color Optional color producer for text color.
 * @param onBlink Callback triggered when the text blinks.
 */
@Composable
internal fun Blinking(
    text: String,
    interval: Long,
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
                delay(timeMillis = interval / 2)
                isVisible = !isVisible
                if (isVisible) onBlink()
            }

            Repeat.Continuous -> {
                do {
                    delay(timeMillis = interval / 2)
                    isVisible = !isVisible
                    if (isVisible) onBlink()
                } while (true)
            }

            is Repeat.TimeBound -> {
                val endTime = Clock.System.now().toEpochMilliseconds() + repeat.duration
                do {
                    delay(timeMillis = interval / 2)
                    isVisible = !isVisible
                    if (isVisible) onBlink()
                } while (Clock.System.now().toEpochMilliseconds() < endTime)
                isVisible = repeat.showAfterComplete
            }

            is Repeat.CountBound -> {
                var repeatCount = 0
                while (repeatCount < repeat.count) {
                    delay(timeMillis = interval / 2)
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