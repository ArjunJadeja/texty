package com.arjunjadeja.texty.styles.normal

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.FadingType
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class FadingStyleTest {

    @Test
    fun fadeInCompletesCallback() = runComposeUiTest {
        var done = false
        val duration = StyleTestFixtures.FAST_ANIMATION_MS
        setContent {
            Texty(
                text = StyleTestFixtures.MEDIUM_TEXT,
                displayStyle = DisplayStyle.Fading(
                    type = FadingType.IN,
                    duration = duration,
                    onComplete = { done = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(duration + 100)
        waitForIdle()
        assertTrue(done)
        onNodeWithTag(TextyTestTags.ROOT).assertExists()
    }

    @Test
    fun fadeOutCompletesCallback() = runComposeUiTest {
        var done = false
        val duration = StyleTestFixtures.FAST_ANIMATION_MS
        setContent {
            Texty(
                text = StyleTestFixtures.MEDIUM_TEXT,
                displayStyle = DisplayStyle.Fading(
                    type = FadingType.OUT,
                    duration = duration,
                    onComplete = { done = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(duration + 100)
        waitForIdle()
        assertTrue(done)
    }
}
