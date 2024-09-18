package com.arjunjadeja.texty

sealed interface Repeat {
    data object Once : Repeat
    data object Infinite : Repeat
    data class TimeBound(val durationInMillis: Long, val showAfterComplete: Boolean) : Repeat
    data class CountBound(val repeatCount: Int, val showAfterComplete: Boolean) : Repeat
}