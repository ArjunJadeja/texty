package com.arjunjadeja.texty.styles.list

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class MotionStyleTest {

    @Test
    fun twoFrameSequenceCompletes() = runComposeUiTest {
        val delay = StyleTestFixtures.FAST_DELAY_MS
        var complete = false
        setContent {
            Texty(
                textList = listOf(StyleTestFixtures.FRAME_A, StyleTestFixtures.FRAME_B),
                displayStyle = ListDisplayStyle.Motion(
                    delayBeforeNext = delay,
                    repeat = Repeat.Once,
                    onComplete = { complete = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(delay * 2 + 50)
        assertTrue(complete)
        onNodeWithTag(TextyTestTags.ROOT).assertTextEquals(StyleTestFixtures.FRAME_B)
    }

    @Test
    fun threeFrameSequenceEndsOnLast() = runComposeUiTest {
        val delay = StyleTestFixtures.FAST_DELAY_MS
        var complete = false
        setContent {
            Texty(
                textList = StyleTestFixtures.MOTION_FRAMES,
                displayStyle = ListDisplayStyle.Motion(
                    delayBeforeNext = delay,
                    repeat = Repeat.Once,
                    onComplete = { complete = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(delay * StyleTestFixtures.MOTION_FRAMES.size + 100)
        assertTrue(complete)
        onNodeWithTag(TextyTestTags.ROOT).assertTextEquals(StyleTestFixtures.FRAME_C)
    }
}
