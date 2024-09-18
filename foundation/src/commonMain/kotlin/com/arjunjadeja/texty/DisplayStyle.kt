package com.arjunjadeja.texty

sealed interface DisplayStyle {

    data class Basic(val onTextDisplayed: () -> Unit = {}) : DisplayStyle {
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

}
