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

/**
 * Defines different display styles for text composables.
 */
sealed interface DisplayStyle {

    /**
     * A basic style that displays text without any animation or effect.
     *
     * @property onTextDisplayed Callback invoked when the text is fully displayed.
     */
    data class Basic(
        val onTextDisplayed: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Basic"
    }

    /**
     * A typing display style that simulates typing effect by displaying characters one by one.
     *
     * @property typingDelayPerChar Delay in milliseconds between each character appearance.
     * @property onTextDisplayed Callback invoked when the typing animation is complete.
     */
    data class Typing(
        val typingDelayPerChar: Long = 80L,
        val onTextDisplayed: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Typing"
    }

    /**
     * A blinking display style that makes the text appear and disappear at specified intervals.
     *
     * @property interval The interval in milliseconds between blinks.
     * @property repeat Defines how often the blinking animation repeats.
     * @property onBlink Callback invoked every time the text blinks.
     */
    data class Blinking(
        val interval: Long = 500L,
        val repeat: Repeat = Repeat.Continuous,
        val onBlink: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Blinking"
    }

    /**
     * A fading display style that gradually changes the opacity of the text.
     *
     * @property type Defines the type of fading effect (e.g., fade in, fade out).
     * @property duration Duration in milliseconds for the fade effect.
     * @property onComplete Callback invoked when the fading animation is complete.
     */
    data class Fading(
        val type: FadingType,
        val duration: Long = 1000L,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Fading"
    }

    /**
     * A sliding display style that moves the text in a specified direction.
     *
     * @property direction The direction in which the text slides (e.g., towards start).
     * @property duration Duration in milliseconds for the sliding animation.
     * @property repeat Defines how often the sliding animation repeats.
     * @property onComplete Callback invoked when the sliding animation completes.
     */
    data class Sliding(
        val direction: SlidingDirection = SlidingDirection.TOWARDS_START,
        val duration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString() = "Sliding"
    }

    /**
     * A scrolling display style that scrolls the text in a specified direction.
     *
     * @property direction The direction in which the text scrolls (e.g., towards top).
     * @property duration Duration in milliseconds for the scrolling animation.
     * @property repeat Defines how often the scrolling animation repeats.
     * @property onComplete Callback invoked when the scrolling animation completes.
     */
    data class Scrolling(
        val direction: ScrollingDirection = ScrollingDirection.TOWARDS_TOP,
        val duration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString() = "Scrolling"
    }

    /**
     * A revealing display style that progressively uncovers the text in a specified pattern.
     *
     * @property cover Specifies the covering type that conceals the text initially.
     * @property pattern Defines the pattern of the revealing effect (e.g., start to end).
     * @property type The type of revealing (e.g., character by character).
     * @property delayBeforeRevealing Delay in milliseconds before starting the reveal effect.
     * @property onComplete Callback invoked when the revealing animation completes.
     */
    data class Revealing(
        val cover: RevealingCover = RevealingCover.Default,
        val pattern: RevealingPattern = RevealingPattern.START_TO_END,
        val type: RevealingType = RevealingType.ByEachCharacter(delayInMillis = 80L),
        val delayBeforeRevealing: Long = 500L,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Revealing"
    }

    /**
     * A combined display style where a text cover sticks for a while before revealing the text.
     *
     * @property cover Text or symbol that initially covers the main text (optional).
     * @property coverStickingDirection Direction in which the cover sticks before revealing.
     * @property coverStickingDelay Delay in milliseconds before the cover starts to stick.
     * @property delayBeforeReveal Delay before revealing the main text after sticking.
     * @property revealingDirection The direction in which the text reveals.
     * @property revealingDelay Delay in milliseconds between each revealing step.
     * @property onComplete Callback invoked when the stick and reveal animation completes.
     */
    data class StickAndReveal(
        val cover: String? = null,
        val coverStickingDirection: TransitionDirection = TransitionDirection.TOP_TO_BOTTOM,
        val coverStickingDelay: Long = 80L,
        val delayBeforeReveal: Long = 1000L,
        val revealingDirection: TransitionDirection = TransitionDirection.BOTTOM_TO_TOP,
        val revealingDelay: Long = 70L,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString() = "Stick and Reveal"
    }
}