package com.arjunjadeja.texty.internal.styles.normal

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.SlidingDirection
import kotlinx.coroutines.delay

@Composable
internal fun StickAndReveal(
    frame: String,
    stickingDelay: Long,
    revealingDelay: Long,
    delayBeforeReveal: Long,
    stickingDirection: SlidingDirection,
    revealingDirection: SlidingDirection,
    cover: String? = null,
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
    val lines = frame.lines()
    val coverChar = cover?.firstOrNull() ?: '⣿'
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(frame) {
        val coveredLines = lines.map { line ->
            line.map { if (it.isWhitespace()) it else coverChar }.joinToString("")
        }
        val maxWidth = lines.maxOf { it.length }

        // Sticking phase
        when (stickingDirection) {
            SlidingDirection.TOP_TO_BOTTOM -> {
                for (line in coveredLines) {
                    displayedText += line.padEnd(maxWidth) + "\n"
                    delay(stickingDelay)
                }
            }

            SlidingDirection.BOTTOM_TO_TOP -> {
                displayedText = "\n".repeat(lines.size)
                delay(stickingDelay)
                for (i in lines.indices.reversed()) {
                    val currentLines = displayedText.lines().toMutableList()
                    currentLines[i] = coveredLines[i].padEnd(maxWidth)
                    displayedText = currentLines.joinToString("\n")
                    delay(stickingDelay)
                }
            }

            SlidingDirection.LEFT_TO_RIGHT -> {
                displayedText = lines.joinToString("\n") { " ".repeat(maxWidth) }
                for (col in 0 until maxWidth) {
                    val currentLines = displayedText.lines().toMutableList()
                    for (row in lines.indices) {
                        if (col < coveredLines[row].length) {
                            currentLines[row] = currentLines[row].replaceRange(
                                col,
                                col + 1,
                                coveredLines[row][col].toString()
                            )
                        }
                    }
                    displayedText = currentLines.joinToString("\n")
                    delay(stickingDelay)
                }
            }

            SlidingDirection.RIGHT_TO_LEFT -> {
                displayedText = lines.joinToString("\n") { " ".repeat(maxWidth) }
                for (col in maxWidth - 1 downTo 0) {
                    val currentLines = displayedText.lines().toMutableList()
                    for (row in lines.indices) {
                        if (col < coveredLines[row].length) {
                            currentLines[row] = currentLines[row].replaceRange(
                                col,
                                col + 1,
                                coveredLines[row][col].toString()
                            )
                        }
                    }
                    displayedText = currentLines.joinToString("\n")
                    delay(stickingDelay)
                }
            }
        }

        delay(delayBeforeReveal)

        // Revealing phase
        when (revealingDirection) {
            SlidingDirection.TOP_TO_BOTTOM -> {
                for (i in lines.indices) {
                    delay(revealingDelay)
                    val currentLines = displayedText.lines().toMutableList()
                    currentLines[i] = lines[i].padEnd(maxWidth)
                    displayedText = currentLines.joinToString("\n")
                }
            }

            SlidingDirection.BOTTOM_TO_TOP -> {
                for (i in lines.indices.reversed()) {
                    delay(revealingDelay)
                    val currentLines = displayedText.lines().toMutableList()
                    currentLines[i] = lines[i].padEnd(maxWidth)
                    displayedText = currentLines.joinToString("\n")
                }
            }

            SlidingDirection.LEFT_TO_RIGHT -> {
                for (col in 0 until maxWidth) {
                    delay(revealingDelay)
                    val currentLines = displayedText.lines().toMutableList()
                    for (row in lines.indices) {
                        if (col < lines[row].length) {
                            currentLines[row] = currentLines[row].replaceRange(
                                col,
                                col + 1,
                                lines[row][col].toString()
                            )
                        }
                    }
                    displayedText = currentLines.joinToString("\n")
                }
            }

            SlidingDirection.RIGHT_TO_LEFT -> {
                for (col in maxWidth - 1 downTo 0) {
                    delay(revealingDelay)
                    val currentLines = displayedText.lines().toMutableList()
                    for (row in lines.indices) {
                        if (col < lines[row].length) {
                            currentLines[row] = currentLines[row].replaceRange(
                                col,
                                col + 1,
                                lines[row][col].toString()
                            )
                        }
                    }
                    displayedText = currentLines.joinToString("\n")
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
