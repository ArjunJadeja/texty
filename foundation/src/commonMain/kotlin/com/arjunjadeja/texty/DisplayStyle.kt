package com.arjunjadeja.texty

sealed interface DisplayStyle {

    data class Basic(
        val onTextDisplayed: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Basic"
    }

    data class Typing(
        val typingDelayPerChar: Long = 80L,
        val onTextDisplayed: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Typing"
    }

    data class Blinking(
        val interval: Long = 500L,
        val repeat: Repeat = Repeat.Continuous,
        val onBlink: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Blinking"
    }

    data class Fading(
        val type: FadingType,
        val duration: Long = 1000L,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Fading"
    }

    data class Sliding(
        val direction: SlidingDirection = SlidingDirection.TOWARDS_START,
        val duration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString() = "Sliding"
    }

    data class Scrolling(
        val direction: ScrollingDirection = ScrollingDirection.TOWARDS_TOP,
        val duration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString() = "Scrolling"
    }

    data class Revealing(
        val cover: RevealingCover = RevealingCover.Default,
        val pattern: RevealingPattern = RevealingPattern.START_TO_END,
        val type: RevealingType = RevealingType.ByEachCharacter(delayInMillis = 80L),
        val delayBeforeRevealing: Long = 500L,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Revealing"
    }

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
