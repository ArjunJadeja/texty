package com.arjunjadeja.texty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.arjunjadeja.texty.design_system.theme.AppTheme
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.arjunjadeja.texty.design_system.properties.Gradients.backgroundGradient
import com.arjunjadeja.texty.ui.screens.MainScreen

@Composable
internal fun App() = AppTheme {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = backgroundGradient,
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding())
                .align(Alignment.TopCenter)
        ) {}

        Navigator(screen = MainScreen()) { navigator ->
            SlideTransition(navigator)
        }

        Surface(
            color = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .height(WindowInsets.safeDrawing.asPaddingValues().calculateBottomPadding())
                .align(Alignment.BottomCenter)
        ) {}
    }
}
