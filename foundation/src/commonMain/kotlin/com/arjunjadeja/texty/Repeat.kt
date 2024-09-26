package com.arjunjadeja.texty

sealed interface Repeat {
    data object Once : Repeat
    data object Continuous : Repeat
    data class TimeBound(val duration: Long, val showAfterComplete: Boolean) : Repeat
    data class CountBound(val count: Int, val showAfterComplete: Boolean) : Repeat
}