package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.Utility
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.components.core.CardElevationType
import com.arjunjadeja.texty.design_system.components.core.TextyCard
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingMedium
import com.arjunjadeja.texty.design_system.properties.AppDimens.sampleDemoCardSize
import com.arjunjadeja.texty.design_system.properties.AppDimens.smallSpacer
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get
import kotlin.time.Duration.Companion.seconds

@Composable
fun TimeKeepingSample(isDemo: Boolean) = SampleCard(
    title = "Time Keeping utilities",
    description = "Sample demonstrating various time keeping formats and options."
) {
    SampleWrappingBox(isDemo = isDemo) {
        Column(
            modifier = Modifier
                .size(sampleDemoCardSize)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFF6D39A),
                            Color(0xFF949B8C),
                            Color(0xFF20597C)
                        )
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(mediumSpacer),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(mediumSpacer))
            LiveUpdateSection()
            CustomFormatsSection()
            UpdateIntervalSection()
        }
    }
}

@Composable
private fun LiveUpdateSection() = TimeSection {
    Row(
        horizontalArrangement = Arrangement.spacedBy(bigSpacer),
        modifier = Modifier.fillMaxWidth()
    ) {
        TimeItem("Live", Modifier.weight(1f)) {
            Texty(
                utility = Utility.TimeKeeping(format = "HH:mm:ss", liveUpdate = true),
                textStyle = TextyStyle.TOP_BAR_TITLE.get()
            )
        }
        TimeItem("Static", Modifier.weight(1f)) {
            Texty(
                utility = Utility.TimeKeeping(format = "HH:mm:ss", liveUpdate = false),
                textStyle = TextyStyle.TOP_BAR_TITLE.get()
            )
        }
    }
}

@Composable
private fun CustomFormatsSection() = TimeSection {
    Column(
        verticalArrangement = Arrangement.spacedBy(smallSpacer),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        TimeItem("Date and Time") {
            Texty(
                utility = Utility.TimeKeeping(
                    format = "yyyy-MM-dd HH:mm:ss",
                    liveUpdate = true
                ),
                textStyle = TextyStyle.DISPLAY_STYLE_VARIATION_NAME.get()
            )
        }
        TimeItem("Date and Day") {
            Texty(
                utility = Utility.TimeKeeping(format = "yyyy-mm-dd EEEE", liveUpdate = false),
                textStyle = TextyStyle.DISPLAY_STYLE_VARIATION_NAME.get()
            )
        }
    }
}

@Composable
private fun UpdateIntervalSection() = TimeSection {
    Row(
        horizontalArrangement = Arrangement.spacedBy(mediumSpacer),
        modifier = Modifier.fillMaxWidth()
    ) {
        TimeItem("Every Second", Modifier.weight(1f)) {
            Texty(
                utility = Utility.TimeKeeping(
                    format = "HH:mm:ss",
                    liveUpdate = true,
                    updateInterval = 1.seconds
                ),
                textStyle = TextyStyle.DISPLAY_STYLE_VARIATION_NAME.get()
            )
        }
        TimeItem("Update in 5 Seconds", Modifier.weight(1f)) {
            Texty(
                utility = Utility.TimeKeeping(
                    format = "HH:mm:ss",
                    liveUpdate = true,
                    updateInterval = 5.seconds
                ),
                textStyle = TextyStyle.DISPLAY_STYLE_VARIATION_NAME.get()
            )
        }
    }
}

@Composable
private fun TimeSection(
    content: @Composable () -> Unit
) = TextyCard(
    elevationType = CardElevationType.SMALL,
    modifier = Modifier
        .padding(horizontal = paddingMedium)
        .fillMaxWidth()
) {
    Column(
        modifier = Modifier.padding(paddingMedium),
        verticalArrangement = Arrangement.spacedBy(smallSpacer)
    ) { content() }
}

@Composable
private fun TimeItem(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
) {
    content()
    Spacer(modifier = Modifier.height(smallSpacer))
    Texty(text = label, textStyle = TextyStyle.CODE_LABEL.get())
}