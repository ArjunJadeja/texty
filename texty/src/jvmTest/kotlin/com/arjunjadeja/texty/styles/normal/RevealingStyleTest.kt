package com.arjunjadeja.texty.styles.normal

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.RevealingPattern
import com.arjunjadeja.texty.RevealingType
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class RevealingStyleTest {

    @Test
    fun startToEndCompletesCallback() = runComposeUiTest {
        var complete = false
        val charDelay = StyleTestFixtures.FAST_DELAY_MS
        setContent {
            Texty(
                text = "ABC",
                displayStyle = DisplayStyle.Revealing(
                    delayBeforeRevealing = StyleTestFixtures.FAST_DELAY_MS,
                    pattern = RevealingPattern.START_TO_END,
                    type = RevealingType.ByEachCharacter(delayInMillis = charDelay),
                    onComplete = { complete = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(StyleTestFixtures.FAST_DELAY_MS + charDelay * 4 + 50)
        assertTrue(complete)
    }

    @Test
    fun centerToSidesCompletesCallback() = runComposeUiTest {
        var complete = false
        setContent {
            Texty(
                text = "XY",
                displayStyle = DisplayStyle.Revealing(
                    pattern = RevealingPattern.CENTER_TO_SIDES,
                    type = RevealingType.ByEachCharacter(delayInMillis = 1L),
                    onComplete = { complete = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(2_000)
        waitForIdle()
        assertTrue(complete)
    }
}
