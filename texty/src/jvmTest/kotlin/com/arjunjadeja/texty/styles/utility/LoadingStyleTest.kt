package com.arjunjadeja.texty.styles.utility

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.LoadingType
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.Utility
import com.arjunjadeja.texty.support.TextyTestTags
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LoadingStyleTest {

    @Test
    fun spinnerComposes() = runLoading(Utility.Loading(LoadingType.Spinner(cycleDurationInMillis = 50)))

    @Test
    fun musicBarComposes() = runLoading(
        Utility.Loading(LoadingType.MusicBar(barCount = 3, cycleDurationInMillis = 50))
    )

    private fun runLoading(utility: Utility.Loading) = runComposeUiTest {
        setContent {
            Box(Modifier.testTag(TextyTestTags.ROOT)) {
                Texty(utility = utility)
            }
        }
        waitForIdle()
        onNodeWithTag(TextyTestTags.ROOT).assertExists()
    }
}
