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
        val repeat: Repeat = Repeat.Infinite,
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

}
