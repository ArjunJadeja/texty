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

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

/**
 * Formats [dateTime] using a subset of date/time pattern tokens.
 *
 * Supported tokens: `yyyy`, `MM`, `dd`, `HH`, `mm`, `ss`, `SSS`, `EEEE`, `EEE`.
 * Literal text may be wrapped in single quotes (for example `'at'`).
 *
 * @param dateTime The instant to format in the given calendar fields.
 * @param format Pattern string; invalid patterns yield a descriptive error string.
 * @return Formatted text, or `"Invalid Format: …"` when the pattern cannot be applied.
 */
internal fun formatDateTime(dateTime: LocalDateTime, format: String): String {
    return try {
        if (!isValidDateTimeFormat(format)) throw IllegalArgumentException("Invalid format string")

        val dayOfWeekFull = dateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val dayOfWeekShort = dayOfWeekFull.take(3)

        format.replace(Regex("'[^']*'")) { it.value }
            .replace("yyyy", dateTime.year.toString().padStart(4, '0'))
            .replace("MM", dateTime.month.number.toString().padStart(2, '0'))
            .replace("dd", dateTime.day.toString().padStart(2, '0'))
            .replace("HH", dateTime.hour.toString().padStart(2, '0'))
            .replace("mm", dateTime.minute.toString().padStart(2, '0'))
            .replace("ss", dateTime.second.toString().padStart(2, '0'))
            .replace("SSS", dateTime.nanosecond.toString().padStart(9, '0').substring(0, 3))
            .replace("EEEE", dayOfWeekFull)
            .replace("EEE", dayOfWeekShort)
            .replace(Regex("'([^']*)'")) { it.groupValues[1] }
    } catch (_: Exception) {
        "Invalid Format: $format"
    }
}

/**
 * Returns the current system time formatted with [format] in the system default time zone.
 */
internal fun formatCurrentTime(format: String): String {
    val now = Clock.System.now()
    val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
    return formatDateTime(localDateTime, format)
}

/**
 * Validates that [format] contains only supported date/time tokens and quoted literals.
 */
internal fun isValidDateTimeFormat(format: String): Boolean {
    val validPatterns = listOf("yyyy", "MM", "dd", "HH", "mm", "ss", "SSS", "EEEE", "EEE")
    val regex = Regex("'[^']*'|(" + validPatterns.joinToString("|") + ")")

    var remainingFormat = format
    while (remainingFormat.isNotEmpty()) {
        val match = regex.find(remainingFormat) ?: return false
        remainingFormat = remainingFormat.substring(match.range.last + 1)
    }
    return true
}
