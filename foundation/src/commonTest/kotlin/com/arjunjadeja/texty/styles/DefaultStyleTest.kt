package com.arjunjadeja.texty

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class TextyTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDefaultStyle() {
        var callbackCalled = false
        composeTestRule.setContent {
            Texty(
                text = "Hello, World!",
                displayStyle = DisplayStyle.Default {
                    callbackCalled = true
                }
            )
        }

        composeTestRule.onNodeWithText("Hello, World!")
            .assertExists()
            .assertIsDisplayed()

        assertEquals(true, callbackCalled, "onDisplayed callback should be called")
    }

    @Test
    fun testTextStyleApplied() {
        composeTestRule.setContent {
            Texty(
                text = "Styled Text",
                displayStyle = DisplayStyle.Default(),
                textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
        }

        composeTestRule.onNodeWithText("Styled Text")
            .assertExists()
            .assertIsDisplayed()
            .assertTextStyle {
                assertEquals(24.sp, this.fontSize)
                assertEquals(FontWeight.Bold, this.fontWeight)
            }
    }

    @Test
    fun testColor() {
        composeTestRule.setContent {
            Texty(
                text = "Colored Text",
                displayStyle = DisplayStyle.Default(),
                color = { Color.Red }
            )
        }

        composeTestRule.onNodeWithText("Colored Text")
            .assertExists()
            .assertIsDisplayed()
            .assertTextColor(Color.Red)
    }

}