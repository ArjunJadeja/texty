package com.arjunjadeja.texty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.Utility
import com.arjunjadeja.texty.base.StyleType
import com.arjunjadeja.texty.design_system.components.SampleScreenTopBar
import com.arjunjadeja.texty.design_system.components.core.CardElevationType
import com.arjunjadeja.texty.design_system.components.core.TextyCard
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.maxWidth
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get
import com.arjunjadeja.texty.design_system.theme.isDesktop
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

data class SampleScreen(val styleType: StyleType) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Box(modifier = Modifier.fillMaxSize()) {
            val topInsets = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = maxWidth)
                    .align(Alignment.Center)
                    .padding(horizontal = paddingBig),
                verticalArrangement = Arrangement.spacedBy(bigSpacer),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = topInsets + paddingBig)
            ) {
                item {
                    SampleScreenTopBar(
                        title = "$styleType Sample",
                        onBackClick = { navigator?.pop() }
                    )
                }
                item { ShowSample(styleType = styleType, isDemo = true) }
                if (!isDesktop()) item { ProTipForMobileScreenType() }
            }
        }
    }
}

@Composable
private fun ShowSample(
    styleType: StyleType,
    isDemo: Boolean
) = when (styleType) {
    is StyleType.DisplayStyleType -> NormalDisplayStyleSample(
        displayStyle = styleType.displayStyle,
        isDemo = isDemo
    )

    is StyleType.ListDisplayStyleType -> ListDisplayStyleSample(
        displayStyle = styleType.displayStyle,
        isDemo = isDemo
    )

    is StyleType.UtilityType -> UtilitySample(
        utility = styleType.utility,
        isDemo = isDemo
    )
}

@Composable
private fun NormalDisplayStyleSample(
    displayStyle: DisplayStyle,
    isDemo: Boolean
) = when (displayStyle) {
    is DisplayStyle.Basic -> BasicSample(isDemo = isDemo)
    is DisplayStyle.Blinking -> BlinkingSample(isDemo = isDemo)
    is DisplayStyle.Fading -> FadingSample(isDemo = isDemo)
    is DisplayStyle.Revealing -> RevealingSample(isDemo = isDemo)
    is DisplayStyle.Scrolling -> ScrollingSample(isDemo = isDemo)
    is DisplayStyle.Sliding -> SlidingSample(isDemo = isDemo)
    is DisplayStyle.StickAndReveal -> StickAndRevealSample(isDemo = isDemo)
    is DisplayStyle.Typing -> TypingSample(isDemo = isDemo)
}

@Composable
private fun ListDisplayStyleSample(
    displayStyle: ListDisplayStyle,
    isDemo: Boolean
) = when (displayStyle) {
    is ListDisplayStyle.Motion -> MotionSample(isDemo = isDemo)
    is ListDisplayStyle.OneByOne -> OneByOneSample(isDemo = isDemo)
    is ListDisplayStyle.ScrollingList -> ScrollingListSample(isDemo = isDemo)
    is ListDisplayStyle.SlidingList -> SlidingListSample(isDemo = isDemo)
}

@Composable
private fun UtilitySample(
    utility: Utility,
    isDemo: Boolean
) = when (utility) {
    is Utility.Loading -> LoadingSample(isDemo = isDemo)
    is Utility.TimeKeeping -> TimeKeepingSample(isDemo = isDemo)
}

@Composable
private fun ProTipForMobileScreenType() = TextyCard(
    elevationType = CardElevationType.MEDIUM,
) {
    Texty(
        text = "If the sample doesn't show correctly try rotating device",
        textStyle = TextyStyle.FOOTER.get(),
        modifier = Modifier.padding(all = paddingBig)
    )
}