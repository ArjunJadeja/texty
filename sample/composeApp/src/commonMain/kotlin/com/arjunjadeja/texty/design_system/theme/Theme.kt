package com.arjunjadeja.texty.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import com.arjunjadeja.texty.design_system.properties.darkScheme
import com.arjunjadeja.texty.design_system.properties.lightScheme

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(false) }

@Composable
internal fun AppTheme(content: @Composable () -> Unit) {
    // val systemIsDark = isSystemInDarkTheme()
    val systemIsDark = false
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState
    ) {
        val isDark by isDarkState
        SystemAppearance(!isDark)
        MaterialTheme(
            colorScheme = if (isDark) darkScheme else lightScheme,
            content = { Surface(content = content) },
            typography = TextyTypography()
        )
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)

internal expect fun isDesktop(): Boolean
