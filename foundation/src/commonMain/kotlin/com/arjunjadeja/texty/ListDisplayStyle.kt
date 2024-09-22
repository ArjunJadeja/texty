package com.arjunjadeja.texty

sealed interface ListDisplayStyle {

    data class Motion(
        val delayBeforeNext: Long = 80L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Motion"
    }

    data class OneByOne(
        val transitionEffect: TransitionEffect = TransitionEffect.BASIC,
        val displayDuration: Long = 200L,
        val transitionInDuration: Long = 100L,
        val transitionOutDuration: Long = 100L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "One By One"
    }

}