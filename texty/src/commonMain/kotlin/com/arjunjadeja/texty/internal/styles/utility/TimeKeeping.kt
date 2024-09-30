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
import kotlinx.datetime.*
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
    var currentTime by remember { mutableStateOf(getCurrentTime(format)) }

    LaunchedEffect(liveUpdate) {
        if (liveUpdate) {
            while (true) {
                delay(updateInterval)
                currentTime = getCurrentTime(format)
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

/**
 * Gets the current time formatted according to the specified format string.
 *
 * @param format The format string defining how the date/time should be formatted.
 * @return The formatted current time as a string.
 */
private fun getCurrentTime(format: String): String {
    val now = Clock.System.now()
    val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
    return formatDateTime(localDateTime, format)
}

/**
 * Formats a given LocalDateTime object according to the specified format.
 *
 * @param dateTime The LocalDateTime object to format.
 * @param format The format string defining how to format the date/time.
 * @return The formatted date/time string or an error message if the format is invalid.
 */
private fun formatDateTime(dateTime: LocalDateTime, format: String): String {
    return try {
        if (!isValidFormat(format)) throw IllegalArgumentException("Invalid format string")

        val dayOfWeekFull = dateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val dayOfWeekShort = dayOfWeekFull.take(3)

        format.replace(Regex("'[^']*'")) { it.value } // Temporarily preserve quoted sections
            .replace("yyyy", dateTime.year.toString().padStart(4, '0'))
            .replace("MM", dateTime.monthNumber.toString().padStart(2, '0'))
            .replace("dd", dateTime.dayOfMonth.toString().padStart(2, '0'))
            .replace("HH", dateTime.hour.toString().padStart(2, '0'))
            .replace("mm", dateTime.minute.toString().padStart(2, '0'))
            .replace("ss", dateTime.second.toString().padStart(2, '0'))
            .replace("SSS", dateTime.nanosecond.toString().padStart(9, '0')
                .substring(0, 3))
            .replace("EEEE", dayOfWeekFull)
            .replace("EEE", dayOfWeekShort)
            .replace(Regex("'([^']*)'")) { it.groupValues[1] } // Restore quoted sections
    } catch (e: Exception) {
        "Invalid Format: $format"
    }
}

/**
 * Checks whether the format string contains only valid date/time patterns.
 *
 * @param format The format string to validate.
 * @return True if the format string is valid; false otherwise.
 */
private fun isValidFormat(format: String): Boolean {
    val validPatterns = listOf("yyyy", "MM", "dd", "HH", "mm", "ss", "SSS", "EEEE", "EEE")
    val regex = Regex("'[^']*'|(" + validPatterns.joinToString("|") + ")")

    var remainingFormat = format
    while (remainingFormat.isNotEmpty()) {
        val match = regex.find(remainingFormat) ?: return false
        remainingFormat = remainingFormat.substring(match.range.last + 1)
    }
    return true
}