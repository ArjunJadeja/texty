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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.design_system.components.DisplayStyleVariation
import com.arjunjadeja.texty.design_system.components.FootNotes
import com.arjunjadeja.texty.design_system.components.MainScreenTopBar
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.AppDimens.maxWidth
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

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

                val displayStyles = listOf(
                    DisplayStyle.Basic(),
                    DisplayStyle.Typing()
                )

                items(displayStyles) { displayStyle ->
                    DisplayStyle(
                        displayStyle = displayStyle,
                        onViewSampleClicked = { navigator?.push(SampleScreen(displayStyle = it)) }
                    )
                }

                item { FootNotes() }
            }
        }
    }
}

@Composable
private fun DisplayStyle(
    displayStyle: DisplayStyle,
    onViewSampleClicked: DisplayStyleHandler
) {
    when (displayStyle) {
        is DisplayStyle.Basic -> {
            TextyDisplayStyleCard(
                displayStyle = displayStyle,
                styleDescription = "A clean, minimalist style that focuses on readability with simple, unadorned typography. Ideal for clear and straightforward text presentation.",
                variations = listOf(
                    DisplayStyleVariation(
                        name = "Basic",
                        code = """
                            Texty(
                                text = "Basic Text",
                                displayStyle = DisplayStyle.Basic()
                            )
                            """.trimIndent(),
                        demoContent = {
                            Texty(
                                text = "Basic Text",
                                textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                            )
                        }
                    )
                ),
                onViewSampleClicked = { onViewSampleClicked(displayStyle) }
            )
        }

        is DisplayStyle.Typing -> {
            TextyDisplayStyleCard(
                displayStyle = displayStyle,
                styleDescription = "A typewriter-inspired style that mimics the appearance of text being typed. Ideal for creating a dynamic, real-time effect.",
                variations = listOf(
                    DisplayStyleVariation(
                        name = "Default Typing",
                        code = """
                            Texty(
                                text = "Typing Text",
                                displayStyle = DisplayStyle.Typing()
                            )
                            """.trimIndent(),
                        demoContent = {
                            Texty(
                                text = "Typing Text",
                                displayStyle = DisplayStyle.Typing(),
                                textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                            )
                        }
                    ),
                    DisplayStyleVariation(
                        name = "Custom Typing Delay",
                        code = """
                            Texty(
                                text = "Typing Text",
                                displayStyle = DisplayStyle.Typing(typingDelayPerChar = 800L)
                            )
                            """.trimIndent(),
                        demoContent = {
                            Texty(
                                text = "Typing Text",
                                displayStyle = DisplayStyle.Typing(typingDelayPerChar = 800L),
                                textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                            )
                        }
                    )
                ),
                onViewSampleClicked = { onViewSampleClicked(displayStyle) }
            )
        }
    }
}
