package com.arjunjadeja.texty

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import com.arjunjadeja.texty.samples.BasicSample
import com.arjunjadeja.texty.samples.BlinkingSample
import com.arjunjadeja.texty.samples.FadingSample
import com.arjunjadeja.texty.samples.LoadingSample
import com.arjunjadeja.texty.samples.MotionSample
import com.arjunjadeja.texty.samples.OneByOneSample
import com.arjunjadeja.texty.samples.RevealingSample
import com.arjunjadeja.texty.samples.ScrollingListSample
import com.arjunjadeja.texty.samples.ScrollingSample
import com.arjunjadeja.texty.samples.SlidingListSample
import com.arjunjadeja.texty.samples.SlidingSample
import com.arjunjadeja.texty.samples.StickAndRevealSample
import com.arjunjadeja.texty.samples.TimeKeepingSample
import com.arjunjadeja.texty.samples.TypingSample
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class SampleScreenTest {

    @Test
    fun normalStyleDemosCompose() = runComposeUiTest {
        runDemos(
            { BasicSample(isDemo = true) },
            { TypingSample(isDemo = true) },
            { BlinkingSample(isDemo = true) },
            { FadingSample(isDemo = true) },
            { SlidingSample(isDemo = true) },
            { ScrollingSample(isDemo = true) },
            { RevealingSample(isDemo = true) },
            { StickAndRevealSample(isDemo = true) },
        )
    }

    @Test
    fun listAndUtilityDemosCompose() = runComposeUiTest {
        runDemos(
            { MotionSample(isDemo = true) },
            { OneByOneSample(isDemo = true) },
            { SlidingListSample(isDemo = true) },
            { ScrollingListSample(isDemo = true) },
            { LoadingSample(isDemo = true) },
            { TimeKeepingSample(isDemo = true) },
        )
    }

    private suspend fun ComposeUiTest.runDemos(vararg demos: @Composable () -> Unit) {
        demos.forEach { demo ->
            setContent { demo() }
            waitForIdle()
        }
    }
}
