package com.arjunjadeja.texty

sealed interface LoadingType {
    data class Spinner(val cycleDurationInMillis: Int = 600) : LoadingType
    data class Circular(val cycleDurationInMillis: Int = 600) : LoadingType
    data class MusicBar(val barCount: Int = 5, val cycleDurationInMillis: Int = 600) : LoadingType
}
