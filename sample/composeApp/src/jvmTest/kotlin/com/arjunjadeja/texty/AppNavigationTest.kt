package com.arjunjadeja.texty

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.arjunjadeja.texty.base.StyleType
import com.arjunjadeja.texty.design_system.theme.AppTheme
import com.arjunjadeja.texty.ui.screens.SampleScreen
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class AppNavigationTest {

    @Test
    fun sampleScreenShowsStyleTitle() = runComposeUiTest {
        setContent {
            AppTheme {
                Navigator(
                    screen = SampleScreen(
                        styleType = StyleType.DisplayStyleType(displayStyle = DisplayStyle.Basic())
                    )
                ) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
        waitForIdle()
        onNodeWithText("Basic Sample", substring = true).assertExists()
    }
}
