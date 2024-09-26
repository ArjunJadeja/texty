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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.FadingType
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Utility
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.base.ListDisplayStyleHandler
import com.arjunjadeja.texty.base.StyleType
import com.arjunjadeja.texty.base.UtilityHandler
import com.arjunjadeja.texty.design_system.components.FootNotes
import com.arjunjadeja.texty.design_system.components.MainScreenTopBar
import com.arjunjadeja.texty.design_system.properties.AppDimens.maxWidth
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer
import com.arjunjadeja.texty.styles.BasicStyle
import com.arjunjadeja.texty.styles.BlinkingStyle
import com.arjunjadeja.texty.styles.FadingStyle
import com.arjunjadeja.texty.styles.LoadingStyle
import com.arjunjadeja.texty.styles.MotionStyle
import com.arjunjadeja.texty.styles.OneByOneStyle
import com.arjunjadeja.texty.styles.RevealingStyle
import com.arjunjadeja.texty.styles.ScrollingListStyle
import com.arjunjadeja.texty.styles.ScrollingStyle
import com.arjunjadeja.texty.styles.SlidingListStyle
import com.arjunjadeja.texty.styles.SlidingStyle
import com.arjunjadeja.texty.styles.StickAndRevealStyle
import com.arjunjadeja.texty.styles.TimeKeepingStyle
import com.arjunjadeja.texty.styles.TypingStyle

class MainScreen : Screen {

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
                item { MainScreenTopBar {} }

                val displayStyles = listOf(
                    DisplayStyle.Basic(),
                    DisplayStyle.Typing(),
                    DisplayStyle.Blinking(),
                    DisplayStyle.Fading(type = FadingType.IN),
                    DisplayStyle.Sliding(),
                    DisplayStyle.Scrolling(),
                    DisplayStyle.Revealing(),
                    DisplayStyle.StickAndReveal()
                )

                val listDisplayStyles = listOf(
                    ListDisplayStyle.Motion(),
                    ListDisplayStyle.OneByOne(),
                    ListDisplayStyle.SlidingList(),
                    ListDisplayStyle.ScrollingList()
                )

                val utilities = listOf(
                    Utility.Loading(),
                    Utility.TimeKeeping()
                )

                items(displayStyles) { displayStyle ->
                    DisplayStyle(
                        displayStyle = displayStyle,
                        onViewSampleClicked = {
                            navigator?.push(
                                SampleScreen(
                                    styleType = StyleType.DisplayStyleType(displayStyle = it)
                                )
                            )
                        }
                    )
                }

                items(listDisplayStyles) { displayStyle ->
                    DisplayStyle(
                        displayStyle = displayStyle,
                        onViewSampleClicked = {
                            navigator?.push(
                                SampleScreen(
                                    styleType = StyleType.ListDisplayStyleType(displayStyle = it)
                                )
                            )
                        }
                    )
                }

                items(utilities) { utility ->
                    DisplayUtility(
                        utility = utility,
                        onViewSampleClicked = {
                            navigator?.push(
                                SampleScreen(
                                    styleType = StyleType.UtilityType(utility = it)
                                )
                            )
                        }
                    )
                }

                item { FootNotes() }
            }
        }
    }
}

@Composable
private fun DisplayStyle(
    displayStyle: DisplayStyle,
    onViewSampleClicked: DisplayStyleHandler
) = when (displayStyle) {
    is DisplayStyle.Basic -> BasicStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Typing -> TypingStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Blinking -> BlinkingStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Fading -> FadingStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Sliding -> SlidingStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Scrolling -> ScrollingStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.Revealing -> RevealingStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is DisplayStyle.StickAndReveal -> StickAndRevealStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )
}

@Composable
private fun DisplayStyle(
    displayStyle: ListDisplayStyle,
    onViewSampleClicked: ListDisplayStyleHandler
) = when (displayStyle) {
    is ListDisplayStyle.Motion -> MotionStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is ListDisplayStyle.OneByOne -> OneByOneStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is ListDisplayStyle.SlidingList -> SlidingListStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )

    is ListDisplayStyle.ScrollingList -> ScrollingListStyle(
        displayStyle = displayStyle,
        onViewSampleClicked = onViewSampleClicked
    )
}

@Composable
private fun DisplayUtility(
    utility: Utility,
    onViewSampleClicked: UtilityHandler
) = when (utility) {
    is Utility.Loading -> LoadingStyle(
        utility = utility,
        onViewSampleClicked = onViewSampleClicked
    )

    is Utility.TimeKeeping -> TimeKeepingStyle(
        utility = utility,
        onViewSampleClicked = onViewSampleClicked
    )
}
