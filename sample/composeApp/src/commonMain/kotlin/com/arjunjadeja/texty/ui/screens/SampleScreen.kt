package com.arjunjadeja.texty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.design_system.properties.Gradients.backgroundGradient
import com.arjunjadeja.texty.design_system.components.SampleScreenTopBar
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer
import kotlinx.coroutines.launch

data class SampleScreen(val displayStyle: DisplayStyle) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val lazyListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

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
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = 800.dp)
                    .align(Alignment.Center)
                    .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.1f))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SampleScreenTopBar(
                    title = "$displayStyle Style Samples",
                    onBackClick = { navigator?.pop() },
                    onTitleClick = {
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(0)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(mediumSpacer))

                Texty("This is the Samples Screen")
            }
        }
    }
}
