package com.arjunjadeja.texty

sealed interface DisplayStyle {
    data class Basic(val onDisplayed: () -> Unit = {}) : DisplayStyle {
        override fun toString(): String = "Basic"
    }
}
