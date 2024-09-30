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

/**
 * Displays fading text with an in or out effect over the specified duration.
 *
 * @param text The text to display.
 * @param type The type of fading (IN or OUT).
 * @param duration Duration of the fading effect.
 * @param modifier Modifier for the text composable.
 * @param textStyle Style to apply to the text.
 * @param onTextLayout Callback when text layout is complete.
 * @param overflow How to handle text overflow.
 * @param softWrap Whether to wrap text at soft breaks.
 * @param maxLines Maximum number of lines to display.
 * @param minLines Minimum number of lines to display.
 * @param color Optional color producer for text color.
 * @param onComplete Callback triggered when the fading effect is complete.
 */
@Composable
internal fun Fading(
    text: String,
    type: FadingType,
    duration: Long,
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
                val progress = (elapsedTime.toFloat() / duration).coerceIn(0f, 1f)
                alpha = when (type) {
                    FadingType.IN -> progress
                    FadingType.OUT -> 1f - progress
                }
                if (elapsedTime >= duration) {
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