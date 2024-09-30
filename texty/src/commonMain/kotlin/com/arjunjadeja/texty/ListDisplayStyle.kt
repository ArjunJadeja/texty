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

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Defines different display styles for list of text components.
 */
sealed interface ListDisplayStyle {

    /**
     * A motion style that animates list items with a delay between each item's appearance.
     *
     * @property delayBeforeNext The delay in milliseconds before displaying the next item.
     * @property repeat Defines how often the motion animation repeats.
     * @property onComplete Callback invoked when the entire list animation completes.
     */
    data class Motion(
        val delayBeforeNext: Long = 80L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Motion"
    }

    /**
     * A style that displays list items one by one with specific transition effects.
     *
     * @property transitionStyle Defines the transition style (e.g., basic, fade, slide).
     * @property displayDuration Duration in milliseconds each item is displayed.
     * @property transitionInDuration Duration in milliseconds for the transition in effect.
     * @property transitionOutDuration Duration in milliseconds for the transition out effect.
     * @property repeat Defines how often the one-by-one animation repeats.
     * @property onComplete Callback invoked when the entire sequence completes.
     */
    data class OneByOne(
        val transitionStyle: TransitionStyle = TransitionStyle.BASIC,
        val displayDuration: Long = 200L,
        val transitionInDuration: Long = 100L,
        val transitionOutDuration: Long = 100L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "One By One"
    }

    /**
     * A sliding list style that moves the list of items in a specified direction.
     *
     * @property separator Optional separator string displayed between list items.
     * @property direction The direction in which the list slides (e.g., towards start).
     * @property duration Duration in milliseconds for the sliding animation.
     * @property repeat Defines how often the sliding animation repeats.
     * @property onComplete Callback invoked when the sliding animation completes.
     */
    data class SlidingList(
        val separator: String? = null,
        val direction: SlidingDirection = SlidingDirection.TOWARDS_START,
        val duration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Sliding List"
    }

    /**
     * A scrolling list style that scrolls items in a specified direction with spacing.
     *
     * @property spacing The space between list items, defined in Dp.
     * @property direction The direction in which the list scrolls (e.g., towards top).
     * @property duration Duration in milliseconds for the scrolling animation.
     * @property repeat Defines how often the scrolling animation repeats.
     * @property onComplete Callback invoked when the scrolling animation completes.
     */
    data class ScrollingList(
        val spacing: Dp = 4.dp,
        val direction: ScrollingDirection = ScrollingDirection.TOWARDS_TOP,
        val duration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Scrolling List"
    }
}