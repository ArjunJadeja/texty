package com.arjunjadeja.texty.styles.normal

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.advanceComposeTime
import com.arjunjadeja.texty.support.assertTextyTagEquals
import com.arjunjadeja.texty.support.typingTotalMillis
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class TypingStyleTest {

    @Test
    fun firesOnTextDisplayedWhenComplete() = runComposeUiTest {
        val text = StyleTestFixtures.SHORT_TEXT
        val delay = StyleTestFixtures.FAST_DELAY_MS
        var displayed = false
        setContent {
            Texty(
                text = text,
                displayStyle = DisplayStyle.Typing(
                    typingDelayPerChar = delay,
                    onTextDisplayed = { displayed = true }
                ),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(typingTotalMillis(text, delay) + 50)
        assertTrue(displayed)
        assertTextyTagEquals(text)
    }

    @Test
    fun zeroDelayShowsFullTextQuickly() = runComposeUiTest {
        val text = StyleTestFixtures.MEDIUM_TEXT
        setContent {
            Texty(
                text = text,
                displayStyle = DisplayStyle.Typing(typingDelayPerChar = 0L),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        advanceComposeTime(100)
        assertTextyTagEquals(text)
    }
}
