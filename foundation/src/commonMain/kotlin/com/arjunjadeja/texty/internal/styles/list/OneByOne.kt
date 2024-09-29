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

package com.arjunjadeja.texty.internal.styles.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.FadingType
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.TransitionStyle
import com.arjunjadeja.texty.internal.styles.normal.Basic
import com.arjunjadeja.texty.internal.styles.normal.Fading
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.datetime.Clock

/**
 * Displays each text from a list one by one with transitions.
 *
 * @param textList List of strings to be displayed sequentially.
 * @param transitionStyle The style of transition used: BASIC, FADING, or TYPING.
 * @param displayDuration Duration for which each text is displayed.
 * @param transitionInDuration Duration of the transition in effect.
 * @param transitionOutDuration Duration of the transition out effect.
 * @param repeat Specifies how the text should repeat: Once, Continuously, TimeBound, or CountBound.
 * @param modifier The modifier to be applied to the text.
 * @param textStyle Style configuration for the text.
 * @param onTextLayout Optional callback for text layout information.
 * @param overflow How text overflow should be handled.
 * @param softWrap Whether text should wrap at soft line breaks.
 * @param maxLines The maximum number of lines to display.
 * @param minLines The minimum number of lines to display.
 * @param color Optional producer for text color.
 * @param onComplete A callback triggered when the sequence completes.
 */
@Composable
internal fun OneByOne(
    textList: List<String>,
    transitionStyle: TransitionStyle,
    displayDuration: Long,
    transitionInDuration: Long,
    transitionOutDuration: Long,
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
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var isDisplaying by remember { mutableStateOf(true) }
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val startTime = Clock.System.now().toEpochMilliseconds()
        var repeatCount = if (repeat is Repeat.CountBound) repeat.count else Int.MAX_VALUE

        while (isActive && (when (repeat) {
                Repeat.Continuous -> true
                Repeat.Once -> repeatCount > 0
                is Repeat.TimeBound -> Clock.System.now().toEpochMilliseconds() - startTime < repeat.duration
                is Repeat.CountBound -> repeatCount > 0
            })
        ) {
            for (index in textList.indices) {
                currentIndex = index
                val currentText = textList[currentIndex]

                // Display text
                isDisplaying = true
                if (transitionStyle == TransitionStyle.TYPING) {
                    for (i in 1..currentText.length) {
                        displayedText = currentText.substring(0, i)
                        delay(transitionInDuration / currentText.length)
                    }
                } else {
                    displayedText = currentText
                    delay(transitionInDuration)
                }

                delay(displayDuration)

                // Transition out text
                val isLastItem = index == textList.lastIndex
                val shouldTransitionOut = !(isLastItem && (
                        (repeat is Repeat.TimeBound && repeat.showAfterComplete) ||
                                (repeat is Repeat.CountBound && repeat.showAfterComplete && repeatCount <= 1)
                        ))

                if (shouldTransitionOut) {
                    isDisplaying = false
                    if (transitionStyle == TransitionStyle.TYPING) {
                        for (i in currentText.length downTo 1) {
                            displayedText = currentText.substring(0, i)
                            delay(transitionOutDuration / currentText.length)
                        }
                        displayedText = ""
                    } else {
                        delay(transitionOutDuration)
                        displayedText = ""
                    }
                }
            }

            when (repeat) {
                Repeat.Once -> break
                Repeat.Continuous -> {}
                is Repeat.TimeBound -> {}
                is Repeat.CountBound -> {
                    repeatCount--
                    if (repeatCount <= 0) {
                        if (!repeat.showAfterComplete) {
                            isDisplaying = false
                            displayedText = ""
                        }
                        break
                    }
                }
            }
        }
        onComplete()
    }

    when (transitionStyle) {
        TransitionStyle.BASIC -> {
            if (isDisplaying) {
                Basic(
                    text = displayedText,
                    modifier = modifier,
                    textStyle = textStyle,
                    onTextLayout = onTextLayout,
                    overflow = overflow,
                    softWrap = softWrap,
                    maxLines = maxLines,
                    minLines = minLines,
                    color = color
                )
            }
        }

        TransitionStyle.FADING -> {
            Fading(
                text = displayedText,
                type = if (isDisplaying) FadingType.IN else FadingType.OUT,
                duration = if (isDisplaying) transitionInDuration else transitionOutDuration,
                modifier = modifier,
                textStyle = textStyle,
                onTextLayout = onTextLayout,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
                color = color
            )
        }

        TransitionStyle.TYPING -> {
            Basic(
                text = displayedText,
                modifier = modifier,
                textStyle = textStyle,
                onTextLayout = onTextLayout,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
                color = color
            )
        }
    }
}