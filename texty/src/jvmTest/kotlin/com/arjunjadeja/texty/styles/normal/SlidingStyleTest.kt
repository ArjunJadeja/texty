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
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class SlidingStyleTest {

    @Test
    fun onceTowardsStartInvokesOnComplete() = runSlidingOnce(SlidingDirection.TOWARDS_START)

    @Test
    fun onceTowardsEndInvokesOnComplete() = runSlidingOnce(SlidingDirection.TOWARDS_END)

    @Test
    fun countBoundInvokesOnCompleteEachCycle() = runComposeUiTest {
        var completions = 0
        val duration = StyleTestFixtures.FAST_ANIMATION_MS
        setContent {
            Box(Modifier.size(240.dp)) {
                Texty(
                    text = StyleTestFixtures.SHORT_TEXT,
                    displayStyle = DisplayStyle.Sliding(
                        duration = duration,
                        repeat = Repeat.CountBound(count = 2, showAfterComplete = true),
                        onComplete = { completions++ }
                    ),
                    modifier = Modifier.testTag(TextyTestTags.ROOT)
                )
            }
        }
        advanceComposeTime(duration * 2 + 200)
        assertTrue(completions >= 2)
    }

    private fun runSlidingOnce(direction: SlidingDirection) = runComposeUiTest {
        var complete = false
        val duration = StyleTestFixtures.FAST_ANIMATION_MS
        setContent {
            Box(Modifier.size(240.dp)) {
                Texty(
                    text = StyleTestFixtures.MEDIUM_TEXT,
                    displayStyle = DisplayStyle.Sliding(
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
