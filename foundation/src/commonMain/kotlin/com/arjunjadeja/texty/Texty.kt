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

package com.arjunjadeja.texty

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.internal.styles.list.*
import com.arjunjadeja.texty.internal.styles.normal.*
import com.arjunjadeja.texty.internal.styles.utility.Loading
import com.arjunjadeja.texty.internal.styles.utility.TimeKeeping

/**
 * Displays text with various display styles, including normal, list, and utility styles.
 *
 * Built on top of Compose Foundation, this composable extends `BasicText` with additional display
 * capabilities such as typing effects, animations, and other unique text behaviors.
 *
 * @param text The text to be displayed.
 * @param displayStyle The display style to be applied to the text.
 * Defaults to [DisplayStyle.Basic].
 * @param modifier Modifier used to adjust the composable's appearance and layout behavior.
 * @param textStyle Specifies the style of the text, including font size, weight, and color.
 * Defaults to [TextStyle.Default].
 * @param onTextLayout Callback invoked with the [TextLayoutResult] when the text is measured and
 * laid out.
 * Useful for observing text measurements and layout details.
 * @param overflow How to handle text overflow when content exceeds the available space.
 * Uses [TextOverflow.Clip] by default.
 * @param softWrap Determines if the text should wrap at line breaks. Defaults to true,
 * consistent with Compose behavior.
 * @param maxLines The maximum number of lines allowed for the text. Defaults to unlimited lines.
 * @param minLines The minimum number of lines required for the text layout, even if less content
 * is present.
 * @param color Optional [ColorProducer] to dynamically set the text color, aligning with
 * Compose’s color configuration.
 */
@Composable
fun Texty(
    text: String,
    displayStyle: DisplayStyle = DisplayStyle.Basic(),
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null
) = when (displayStyle) {
    is DisplayStyle.Basic -> Basic(
        text = text,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onTextDisplayed = displayStyle.onTextDisplayed
    )

    is DisplayStyle.Typing -> Typing(
        text = text,
        typingDelayPerChar = displayStyle.typingDelayPerChar,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onTextDisplayed = displayStyle.onTextDisplayed
    )

    is DisplayStyle.Blinking -> Blinking(
        text = text,
        interval = displayStyle.interval,
        repeat = displayStyle.repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onBlink = displayStyle.onBlink
    )

    is DisplayStyle.Fading -> Fading(
        text = text,
        type = displayStyle.type,
        duration = displayStyle.duration,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )

    is DisplayStyle.Sliding -> Sliding(
        text = text,
        direction = displayStyle.direction,
        duration = displayStyle.duration,
        repeat = displayStyle.repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )

    is DisplayStyle.Scrolling -> Scrolling(
        text = text,
        direction = displayStyle.direction,
        duration = displayStyle.duration,
        repeat = displayStyle.repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )

    is DisplayStyle.Revealing -> Revealing(
        text = text,
        cover = displayStyle.cover,
        pattern = displayStyle.pattern,
        type = displayStyle.type,
        delayBeforeRevealing = displayStyle.delayBeforeRevealing,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )

    is DisplayStyle.StickAndReveal -> StickAndReveal(
        frame = text,
        cover = displayStyle.cover,
        coverStickingDirection = displayStyle.coverStickingDirection,
        coverStickingDelay = displayStyle.coverStickingDelay,
        delayBeforeReveal = displayStyle.delayBeforeReveal,
        revealingDirection = displayStyle.revealingDirection,
        revealingDelay = displayStyle.revealingDelay,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )
}

/**
 * Displays a list of texts with various display styles.
 *
 * @param textList A list of strings to be displayed.
 * @param displayStyle The display style to be applied to the list of text.
 * @param modifier Modifier applied to adjust the composable's appearance and layout behavior.
 * @param textStyle The style to apply to the displayed text, including font and color configurations.
 * @param onTextLayout Callback invoked with the layout results when the text is measured and laid out.
 * @param overflow Specifies how text content behaves when exceeding the available space.
 * @param softWrap If true, text wraps at soft line breaks. Defaults to true.
 * @param maxLines The maximum number of lines allowed for the text. Defaults to unlimited.
 * @param minLines The minimum number of lines required for the text layout.
 * @param color Optional [ColorProducer] for dynamically setting the text color.
 */
@Composable
fun Texty(
    textList: List<String>,
    displayStyle: ListDisplayStyle,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null
) = when (displayStyle) {
    is ListDisplayStyle.Motion -> Motion(
        frames = textList,
        delayBeforeNext = displayStyle.delayBeforeNext,
        repeat = displayStyle.repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )

    is ListDisplayStyle.OneByOne -> OneByOne(
        textList = textList,
        transitionStyle = displayStyle.transitionStyle,
        displayDuration = displayStyle.displayDuration,
        transitionInDuration = displayStyle.transitionInDuration,
        transitionOutDuration = displayStyle.transitionOutDuration,
        repeat = displayStyle.repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )

    is ListDisplayStyle.SlidingList -> SlidingList(
        textList = textList,
        separator = displayStyle.separator,
        slidingDirection = displayStyle.direction,
        slidingDuration = displayStyle.duration,
        repeat = displayStyle.repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )

    is ListDisplayStyle.ScrollingList -> ScrollingList(
        textList = textList,
        spacing = displayStyle.spacing,
        scrollingDirection = displayStyle.direction,
        scrollDuration = displayStyle.duration,
        repeat = displayStyle.repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = displayStyle.onComplete
    )
}

/**
 * Displays utility-based text styles, such as loading and timekeeping.
 *
 * @param utility The utility to apply.
 * @param modifier Modifier applied to adjust the composable's appearance and layout behavior.
 * @param textStyle The style to apply to the displayed text, including font and color configurations.
 * @param onTextLayout Callback invoked with the layout results when the text is measured and laid out.
 * @param overflow Specifies how text content behaves when exceeding the available space.
 * @param softWrap If true, text wraps at soft line breaks. Defaults to true.
 * @param maxLines The maximum number of lines allowed for the text. Defaults to unlimited.
 * @param minLines The minimum number of lines required for the text layout.
 * @param color Optional [ColorProducer] for dynamically setting the text color.
 */
@Composable
fun Texty(
    utility: Utility,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    color: ColorProducer? = null
) = when (utility) {
    is Utility.Loading -> Loading(
        loadingType = utility.type,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color
    )

    is Utility.TimeKeeping -> TimeKeeping(
        format = utility.format,
        liveUpdate = utility.liveUpdate,
        updateInterval = utility.updateInterval,
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