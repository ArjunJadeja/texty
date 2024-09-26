package com.arjunjadeja.texty

sealed interface RevealingType {
    data class ByTotalTime(val durationInMillis: Long) : RevealingType
    data class ByEachCharacter(val delayInMillis: Long) : RevealingType
}