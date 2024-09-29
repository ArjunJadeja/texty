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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.internal.styles.normal.Sliding

/**
 * Displays a list of text items sliding in a specified direction.
 *
 * @param textList List of strings to be displayed as a sliding sequence.
 * @param separator Optional separator string to be displayed between items.
 * @param slidingDirection Direction of the sliding animation.
 * @param slidingDuration Duration of the sliding animation.
 * @param repeat Specifies how the sliding should repeat: Once, Continuously, TimeBound, or CountBound.
 * @param modifier The modifier to be applied to the sliding list.
 * @param textStyle Style configuration for the text.
 * @param onTextLayout Optional callback for text layout information.
 * @param overflow How text overflow should be handled.
 * @param softWrap Whether text should wrap at soft line breaks.
 * @param maxLines The maximum number of lines to display.
 * @param minLines The minimum number of lines to display.
 * @param color Optional producer for text color.
 * @param onComplete A callback triggered when the sliding sequence completes.
 */
@Composable
internal fun SlidingList(
    textList: List<String>,
    separator: String?,
    slidingDirection: SlidingDirection,
    slidingDuration: Long,
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
    Sliding(
        text = textList.joinToString(separator = separator ?: " "),
        direction = slidingDirection,
        duration = slidingDuration,
        repeat = repeat,
        modifier = modifier,
        textStyle = textStyle,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        color = color,
        onComplete = onComplete
    )
}