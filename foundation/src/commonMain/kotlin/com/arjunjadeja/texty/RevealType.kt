package com.arjunjadeja.texty

sealed interface RevealType {
    data class ByTotalTime(val durationInMillis: Long) : RevealType
    data class ByEachCharacter(val delayInMillis: Long) : RevealType
}