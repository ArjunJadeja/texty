package com.arjunjadeja.texty

sealed interface RevealingCover {
    data object Default : RevealingCover
    data class Custom(val cover: String) : RevealingCover
}