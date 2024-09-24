package com.arjunjadeja.texty.base

import androidx.compose.runtime.Composable

data class StyleVariation(
    val name: String,
    val code: String,
    val demoContent: @Composable () -> Unit
)