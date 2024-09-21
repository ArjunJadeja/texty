package com.arjunjadeja.texty

import kotlin.time.Duration.Companion.seconds

sealed interface Utility {

    data class Loading(
        val type: LoadingType = LoadingType.Spinner()
    ) : Utility {
        override fun toString(): String = "Loading"
    }

    data class TimeKeeping(
        val format: String = "HH:mm:ss",
        val liveUpdate: Boolean = false,
        val updateInterval: kotlin.time.Duration = 1.seconds
    ) : Utility {
        override fun toString(): String = "Time Keeping"
    }

}