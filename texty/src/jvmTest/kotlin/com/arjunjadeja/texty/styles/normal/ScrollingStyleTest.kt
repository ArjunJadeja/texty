package com.arjunjadeja.texty.styles.normal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.ScrollingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class ScrollingStyleTest {

    @Test
    fun onceTowardsTopCompletes() = runScrollingOnce(ScrollingDirection.TOWARDS_TOP)

    @Test
    fun onceTowardsBottomCompletes() = runScrollingOnce(ScrollingDirection.TOWARDS_BOTTOM)

    private fun runScrollingOnce(direction: ScrollingDirection) = runComposeUiTest {
        var complete = false
        val duration = StyleTestFixtures.FAST_ANIMATION_MS
        setContent {
            Box(Modifier.size(200.dp, 120.dp)) {
                Texty(
                    text = "Line1\nLine2\nLine3",
                    displayStyle = DisplayStyle.Scrolling(
                        direction = direction,
                        duration = duration,
                        repeat = Repeat.Once,
                        onComplete = { complete = true }
                    ),
                    modifier = Modifier.testTag(TextyTestTags.ROOT)
                )
            }
        }
        advanceComposeTime(duration + 100)
        assertTrue(complete)
        onNodeWithTag(TextyTestTags.ROOT).assertExists()
    }
}
