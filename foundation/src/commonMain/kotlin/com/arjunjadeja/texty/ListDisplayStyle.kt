package com.arjunjadeja.texty

sealed interface ListDisplayStyle {

    data class Motion(
        val frameDisplayDelay: Long = 80L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Motion"
    }

}