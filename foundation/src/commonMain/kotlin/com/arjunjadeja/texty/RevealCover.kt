package com.arjunjadeja.texty

sealed interface RevealCover {
    data object Default : RevealCover
    data class Custom(val cover: String) : RevealCover
}