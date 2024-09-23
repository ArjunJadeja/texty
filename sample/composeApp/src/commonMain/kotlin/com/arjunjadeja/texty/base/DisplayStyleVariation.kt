package com.arjunjadeja.texty.base

import androidx.compose.runtime.Composable

data class DisplayStyleVariation(
    val name: String,
    val code: String,
    val demoContent: @Composable () -> Unit
)