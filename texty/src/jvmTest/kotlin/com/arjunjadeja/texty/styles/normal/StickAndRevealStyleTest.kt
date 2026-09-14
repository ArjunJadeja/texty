package com.arjunjadeja.texty.styles.normal

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.TransitionDirection
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class StickAndRevealStyleTest {

    @Test
    fun completesWithCover() = runComposeUiTest {
        var complete = false
        setContent {
            Texty(
                text = "Go",
                displayStyle = DisplayStyle.StickAndReveal(
                    cover = "#",
                    coverStickingDirection = TransitionDirection.TOP_TO_BOTTOM,
                    coverStickingDelay = 1L,
                    delayBeforeReveal = 1L,
                    revealingDirection = TransitionDirection.BOTTOM_TO_TOP,
                    revealingDelay = 1L,
                    onComplete = { complete = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(500)
        assertTrue(complete)
        onNodeWithTag(TextyTestTags.ROOT).assertExists()
    }

    @Test
    fun completesWithoutExplicitCover() = runComposeUiTest {
        var complete = false
        setContent {
            Texty(
                text = StyleTestFixtures.SHORT_TEXT,
                displayStyle = DisplayStyle.StickAndReveal(
                    cover = null,
                    coverStickingDelay = 1L,
                    delayBeforeReveal = 1L,
                    revealingDelay = 1L,
                    onComplete = { complete = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(500)
        assertTrue(complete)
    }
}
