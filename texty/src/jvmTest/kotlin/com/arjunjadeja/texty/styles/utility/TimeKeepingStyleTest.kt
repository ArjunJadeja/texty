package com.arjunjadeja.texty.styles.utility

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.Utility
import com.arjunjadeja.texty.internal.styles.utility.formatDateTime
import com.arjunjadeja.texty.internal.styles.utility.isValidDateTimeFormat
import com.arjunjadeja.texty.support.TextyTestTags
import kotlinx.datetime.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class TimeKeepingStyleTest {

    private val sample = LocalDateTime(2024, 6, 15, 14, 30, 45, nanosecond = 123_000_000)

    @Test
    fun formatsStandardDateTimePattern() {
        assertEquals("2024-06-15", formatDateTime(sample, "yyyy-MM-dd"))
        assertEquals("14:30:45", formatDateTime(sample, "HH:mm:ss"))
    }

    @Test
    fun rejectsInvalidPattern() {
        assertFalse(isValidDateTimeFormat("yyyy-MM-dd xyz"))
        assertTrue(formatDateTime(sample, "not-valid").startsWith("Invalid Format:"))
    }

    @Test
    fun composableShowsTimeWhenLiveUpdateDisabled() = runComposeUiTest {
        setContent {
            Texty(
                utility = Utility.TimeKeeping(format = "yyyy", liveUpdate = false),
                modifier = Modifier.testTag(TextyTestTags.ROOT)
            )
        }
        onNodeWithTag(TextyTestTags.ROOT).assertExists()
    }
}
