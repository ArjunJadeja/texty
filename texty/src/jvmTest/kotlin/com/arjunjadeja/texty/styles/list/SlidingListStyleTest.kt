package com.arjunjadeja.texty.styles.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.unit.dp
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class SlidingListStyleTest {

    @Test
    fun onceInvokesOnComplete() = runComposeUiTest {
        var complete = false
        val duration = StyleTestFixtures.FAST_ANIMATION_MS
        setContent {
            Box(Modifier.size(280.dp)) {
                Texty(
                    textList = StyleTestFixtures.LIST_ITEMS,
                    displayStyle = ListDisplayStyle.SlidingList(
                        separator = "|",
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
    }

    @Test
    fun composesJoinedListWithSeparator() = runComposeUiTest {
        setContent {
            Box(Modifier.size(280.dp)) {
                Texty(
                    textList = StyleTestFixtures.LIST_ITEMS,
                    displayStyle = ListDisplayStyle.SlidingList(
                        separator = "|",
                        duration = StyleTestFixtures.FAST_ANIMATION_MS,
                        repeat = Repeat.Once
                    ),
                    modifier = Modifier.testTag(TextyTestTags.ROOT)
                )
            }
        }
        waitForIdle()
        onNodeWithTag(TextyTestTags.ROOT).assertExists()
    }
}
