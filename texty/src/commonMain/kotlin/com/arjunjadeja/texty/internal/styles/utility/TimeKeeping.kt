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

package com.arjunjadeja.texty.internal.styles.utility

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

/**
 * A composable function that displays the current time based on the provided format.
 * It updates periodically if `liveUpdate` is true.
 *
 * @param format The format of the time string. Supports patterns like "yyyy", "MM", "dd", etc.
 * @param liveUpdate Whether the displayed time should update continuously.
 * @param updateInterval The interval at which the time updates if `liveUpdate` is enabled.
 * @param modifier The modifier to be applied to the text.
 * @param textStyle The style of the text.
 * @param onTextLayout Optional callback when the text layout is completed.
 * @param overflow How visual overflow should be handled.
 * @param softWrap Whether the text should break at soft line breaks.
 * @param maxLines The maximum number of lines to display.
 * @param minLines The minimum number of lines to display.
 * @param color Optional color producer for text color.
 */
@Composable
internal fun TimeKeeping(
    format: String,
    liveUpdate: Boolean,
    updateInterval: kotlin.time.Duration = 1.seconds,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null
) {
    var currentTime by remember { mutableStateOf(formatCurrentTime(format)) }

    LaunchedEffect(liveUpdate) {
        if (liveUpdate) {
            while (true) {
                delay(updateInterval)
                currentTime = formatCurrentTime(format)
            }
        }
    }

    BasicText(
        text = currentTime,
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