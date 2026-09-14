package com.arjunjadeja.texty.styles.list

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.TransitionStyle
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class OneByOneStyleTest {

    @Test
    fun basicTransitionCompletesOnce() = runOneByOne(TransitionStyle.BASIC)

    @Test
    fun typingTransitionCompletesOnce() = runOneByOne(TransitionStyle.TYPING)

    @Test
    fun fadingTransitionCompletesOnce() = runOneByOne(TransitionStyle.FADING)

    private fun runOneByOne(style: TransitionStyle) = runComposeUiTest {
        var complete = false
        setContent {
            Texty(
                textList = StyleTestFixtures.LIST_ITEMS,
                displayStyle = ListDisplayStyle.OneByOne(
                    transitionStyle = style,
                    displayDuration = 2L,
                    transitionInDuration = 2L,
                    transitionOutDuration = 2L,
                    repeat = Repeat.Once,
                    onComplete = { complete = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(500)
        assertTrue(complete)
    }
}
