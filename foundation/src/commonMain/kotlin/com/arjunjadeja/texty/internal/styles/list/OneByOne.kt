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
import com.arjunjadeja.texty.TransitionEffect
import com.arjunjadeja.texty.internal.styles.normal.Basic
import com.arjunjadeja.texty.internal.styles.normal.Fading
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.datetime.Clock

@Composable
internal fun OneByOne(
    textList: List<String>,
    transitionEffect: TransitionEffect,
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
        var repeatCount = if (repeat is Repeat.CountBound) repeat.repeatCount else Int.MAX_VALUE

        while (isActive && (when (repeat) {
                Repeat.Continuous -> true
                Repeat.Once -> repeatCount > 0
                is Repeat.TimeBound -> Clock.System.now()
                    .toEpochMilliseconds() - startTime < repeat.durationInMillis

                is Repeat.CountBound -> repeatCount > 0
            })
        ) {
            for (index in textList.indices) {
                currentIndex = index
                val currentText = textList[currentIndex]

                // Display text
                isDisplaying = true
                if (transitionEffect == TransitionEffect.TYPING) {
                    for (i in 1..currentText.length) {
                        displayedText = currentText.substring(0, i)
                        delay(transitionInDuration / currentText.length)
                    }
                } else {
                    displayedText = currentText
                    delay(transitionInDuration)
                }

                delay(displayDuration)

                // Transition out text (skip for last item if showAfterComplete is true)
                val isLastItem = index == textList.lastIndex
                val shouldTransitionOut = !(isLastItem && (
                        (repeat is Repeat.TimeBound && repeat.showAfterComplete) ||
                                (repeat is Repeat.CountBound && repeat.showAfterComplete && repeatCount <= 1)
                        ))

                if (shouldTransitionOut) {
                    isDisplaying = false
                    if (transitionEffect == TransitionEffect.TYPING) {
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

    when (transitionEffect) {
        TransitionEffect.BASIC -> {
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

        TransitionEffect.FADING -> {
            Fading(
                text = displayedText,
                type = if (isDisplaying) FadingType.IN else FadingType.OUT,
                durationInMillis = if (isDisplaying) transitionInDuration else transitionOutDuration,
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

        TransitionEffect.TYPING -> {
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