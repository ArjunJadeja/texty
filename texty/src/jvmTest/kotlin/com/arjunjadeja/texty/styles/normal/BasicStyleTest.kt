package com.arjunjadeja.texty.styles.normal

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.support.StyleTestFixtures
import com.arjunjadeja.texty.support.TextyTestTags
import com.arjunjadeja.texty.support.assertTextyTagEquals
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class BasicStyleTest {

    @Test
    fun showsFullTextImmediately() = runComposeUiTest {
        setContent {
            Texty(
                text = StyleTestFixtures.MEDIUM_TEXT,
                displayStyle = DisplayStyle.Basic(),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        assertTextyTagEquals(StyleTestFixtures.MEDIUM_TEXT)
    }

    @Test
    fun invokesOnTextDisplayedOnce() = runComposeUiTest {
        var count = 0
        setContent {
            Texty(
                text = StyleTestFixtures.SHORT_TEXT,
                displayStyle = DisplayStyle.Basic(onTextDisplayed = { count++ }),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        waitForIdle()
        assertEquals(1, count)
    }

    @Test
    fun emptyStringRendersWithoutCrash() = runComposeUiTest {
        setContent {
            Texty(
                text = "",
                displayStyle = DisplayStyle.Basic(),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        assertTextyTagEquals("")
    }
}
