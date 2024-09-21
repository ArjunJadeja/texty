package com.arjunjadeja.texty

sealed interface Utility {

    data class Loading(
        val type: LoadingType = LoadingType.Spinner()
    ) : Utility {
        override fun toString(): String = "Loading"
    }

}