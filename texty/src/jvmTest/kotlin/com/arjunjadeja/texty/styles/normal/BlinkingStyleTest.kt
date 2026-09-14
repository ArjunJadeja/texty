package com.arjunjadeja.texty.styles.normal

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class BlinkingStyleTest {

    @Test
    fun countBoundInvokesOnBlinkExpectedTimes() = runComposeUiTest {
        val interval = StyleTestFixtures.FAST_BLINK_INTERVAL_MS
        var blinks = 0
        setContent {
            Texty(
                text = StyleTestFixtures.SHORT_TEXT,
                displayStyle = DisplayStyle.Blinking(
                    interval = interval,
                    repeat = Repeat.CountBound(count = 2, showAfterComplete = true),
                    onBlink = { blinks++ }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(interval * 4)
        assertEquals(2, blinks)
        onNodeWithTag(TextyTestTags.ROOT).assertIsDisplayed()
    }

    @Test
    fun onceShowsTextAfterHalfInterval() = runComposeUiTest {
        val interval = StyleTestFixtures.FAST_BLINK_INTERVAL_MS
        setContent {
            Texty(
                text = StyleTestFixtures.MEDIUM_TEXT,
                displayStyle = DisplayStyle.Blinking(
                    interval = interval,
                    repeat = Repeat.Once
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(interval)
        onNodeWithTag(TextyTestTags.ROOT).assertIsDisplayed()
    }

    @Test
    fun countBoundHidesTextWhenShowAfterCompleteFalse() = runComposeUiTest {
        val interval = StyleTestFixtures.FAST_BLINK_INTERVAL_MS
        setContent {
            Texty(
                text = StyleTestFixtures.SHORT_TEXT,
                displayStyle = DisplayStyle.Blinking(
                    interval = interval,
                    repeat = Repeat.CountBound(count = 1, showAfterComplete = false)
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(interval * 4)
        waitForIdle()
        onNodeWithTag(TextyTestTags.ROOT).assertDoesNotExist()
    }
}
