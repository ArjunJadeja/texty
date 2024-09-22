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
        val blinkInterval: Long = 500L,
        val repeat: Repeat = Repeat.Continuous,
        val onBlink: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Blinking"
    }

    data class Fading(
        val type: FadingType,
        val durationInMillis: Long = 1000L,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Fading"
    }

    data class Revealing(
        val delayBeforeRevealing: Long = 500L,
        val pattern: RevealPattern = RevealPattern.START_TO_END,
        val type: RevealType = RevealType.ByEachCharacter(delayInMillis = 80L),
        val cover: RevealCover = RevealCover.Default,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString(): String = "Revealing"
    }

    data class StickAndReveal(
        val stickingDelay: Long = 80L,
        val revealingDelay: Long = 70L,
        val delayBeforeReveal: Long = 1000L,
        val stickingDirection: TransitionDirection = TransitionDirection.TOP_TO_BOTTOM,
        val revealingDirection: TransitionDirection = TransitionDirection.BOTTOM_TO_TOP,
        val cover: String? = null,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString() = "Stick and Reveal"
    }

    data class Sliding(
        val slidingDirection: SlidingDirection = SlidingDirection.TowardsStart,
        val slideDuration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : DisplayStyle {
        override fun toString() = "Sliding"
    }

}
