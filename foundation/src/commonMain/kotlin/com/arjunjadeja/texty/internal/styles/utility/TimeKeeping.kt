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

private fun getCurrentTime(format: String): String {
    val now = Clock.System.now()
    val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
    return formatDateTime(localDateTime, format)
}

private fun formatDateTime(dateTime: LocalDateTime, format: String): String {
    return try {
        if (!isValidFormat(format)) throw IllegalArgumentException("Invalid format string")

        format.replace(Regex("'[^']*'")) { it.value }  // Temporarily replace quoted sections
            .replace("yyyy", dateTime.year.toString().padStart(4, '0'))
            .replace("MM", dateTime.monthNumber.toString().padStart(2, '0'))
            .replace("dd", dateTime.dayOfMonth.toString().padStart(2, '0'))
            .replace("HH", dateTime.hour.toString().padStart(2, '0'))
            .replace("mm", dateTime.minute.toString().padStart(2, '0'))
            .replace("ss", dateTime.second.toString().padStart(2, '0'))
            .replace("SSS", dateTime.nanosecond.toString().padStart(9, '0').substring(0, 3))
            .replace(Regex("'([^']*)'")) { it.groupValues[1] }  // Restore quoted sections
    } catch (e: Exception) {
        "Invalid Format: $format"
    }
}

private fun isValidFormat(format: String): Boolean {
    val validPatterns = listOf("yyyy", "MM", "dd", "HH", "mm", "ss", "SSS")
    val regex = Regex("'[^']*'|(" + validPatterns.joinToString("|") + ")")

    var remainingFormat = format
    while (remainingFormat.isNotEmpty()) {
        val match = regex.find(remainingFormat) ?: return false
        remainingFormat = remainingFormat.substring(match.range.last + 1)
    }
    return true
}
