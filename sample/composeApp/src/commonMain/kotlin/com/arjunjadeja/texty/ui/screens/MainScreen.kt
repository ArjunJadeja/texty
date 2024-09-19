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
import com.arjunjadeja.texty.FadingType
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.design_system.components.FootNotes
import com.arjunjadeja.texty.design_system.components.MainScreenTopBar
import com.arjunjadeja.texty.design_system.properties.AppDimens.maxWidth
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer
import com.arjunjadeja.texty.display_style_cards.BasicStyleCard
import com.arjunjadeja.texty.display_style_cards.BlinkingStyleCard
import com.arjunjadeja.texty.display_style_cards.FadingStyleCard
import com.arjunjadeja.texty.display_style_cards.TypingStyleCard

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
                    DisplayStyle.Typing(),
                    DisplayStyle.Blinking(),
                    DisplayStyle.Fading(type = FadingType.IN)
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
) = when (displayStyle) {
    is DisplayStyle.Basic -> BasicStyleCard(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Typing -> TypingStyleCard(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Blinking -> BlinkingStyleCard(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Fading -> FadingStyleCard(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )
}
