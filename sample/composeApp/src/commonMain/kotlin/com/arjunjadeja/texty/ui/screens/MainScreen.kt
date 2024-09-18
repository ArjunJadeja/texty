package com.arjunjadeja.texty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.design_system.components.DisplayStyleVariation
import com.arjunjadeja.texty.design_system.components.FootNotes
import com.arjunjadeja.texty.design_system.components.MainScreenTopBar
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.AppDimens.maxWidth
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Box(modifier = Modifier.fillMaxSize()) {
            val topInsets = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = maxWidth)
                    .align(Alignment.Center)
                    .padding(horizontal = paddingBig),
                verticalArrangement = Arrangement.spacedBy(bigSpacer),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = topInsets + paddingBig)
            ) {
                item { MainScreenTopBar {} }
                item {
                    TextyDisplayStyleCard(
                        displayStyle = DisplayStyle.Basic(),
                        styleDescription = "A clean, minimalist style that focuses on readability with simple, unadorned typography. Ideal for clear and straightforward text presentation.",
                        variations = listOf(
                            DisplayStyleVariation(
                                name = "Basic",
                                code = """
                                Texty(
                                    text = "Simple Text",
                                    displayStyle = DisplayStyle.Basic()
                                )
                                """.trimIndent(),
                                demoContent = {
                                    Texty(
                                        text = "Simple Text",
                                        textStyle = MaterialTheme.typography.displayMedium.copy(
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                    )
                                }
                            )
                        ),
                        onViewSampleClicked = { navigator?.push(SampleScreen(DisplayStyle.Basic())) }
                    )
                }
                item { FootNotes() }
            }
        }
    }
}
