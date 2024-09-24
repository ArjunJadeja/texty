package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.arjunjadeja.texty.LoadingType
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

@Composable
fun LoadingSample(isDemo: Boolean) = SampleCard(
    title = "Loading utilities",
    description = "Sample demonstrating various loading utilities."
) {
    SampleWrappingBox(isDemo = isDemo) {
        Column(
            modifier = Modifier
                .size(sampleDemoCardSize)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF42C8A2),
                            Color(0xFF2D969F),
                            Color(0xFF195F9D)
                        )
                    )
                ),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            SpinnerSection()
            CircularSection()
            MusicBarSection()
        }
    }
}

@Composable
private fun SpinnerSection() {
    LoadingSection("Spinners") {
        Row(horizontalArrangement = Arrangement.spacedBy(bigSpacer)) {
            LoadingItem("Default") {
                Texty(
                    utility = Utility.Loading(LoadingType.Spinner()),
                    textStyle = TextyStyle.TOP_BAR_TITLE.get()
                )
            }
            LoadingItem("Slow") {
                Texty(
                    utility = Utility.Loading(LoadingType.Spinner(cycleDurationInMillis = 3000)),
                    textStyle = TextyStyle.TOP_BAR_TITLE.get()
                )
            }
            LoadingItem("fast") {
                Texty(
                    utility = Utility.Loading(LoadingType.Spinner(cycleDurationInMillis = 500)),
                    textStyle = TextyStyle.TOP_BAR_TITLE.get()
                )
            }
        }
    }
}

@Composable
private fun CircularSection() {
    LoadingSection("Rotations") {
        Row(horizontalArrangement = Arrangement.spacedBy(bigSpacer)) {
            LoadingItem("Default") {
                Texty(
                    utility = Utility.Loading(LoadingType.Circular()),
                    textStyle = TextyStyle.TOP_BAR_TITLE.get()
                )
            }
            LoadingItem("Slow") {
                Texty(
                    utility = Utility.Loading(LoadingType.Circular(cycleDurationInMillis = 3000)),
                    textStyle = TextyStyle.TOP_BAR_TITLE.get()
                )
            }
            LoadingItem("Fast") {
                Texty(
                    utility = Utility.Loading(LoadingType.Circular(cycleDurationInMillis = 500)),
                    textStyle = TextyStyle.TOP_BAR_TITLE.get()
                )
            }
        }
    }
}

@Composable
private fun MusicBarSection() = LoadingSection("Music Bars") {
    Row(horizontalArrangement = Arrangement.spacedBy(mediumSpacer)) {
        LoadingItem("Default") {
            Texty(
                utility = Utility.Loading(LoadingType.MusicBar()),
                textStyle = TextyStyle.DISPLAY_STYLE_VARIATION_NAME.get()
            )
        }
        LoadingItem("3 Bars") {
            Texty(
                utility = Utility.Loading(LoadingType.MusicBar(barCount = 3)),
                textStyle = TextyStyle.DISPLAY_STYLE_VARIATION_NAME.get()
            )
        }
    }
}

@Composable
private fun LoadingSection(
    title: String,
    content: @Composable () -> Unit
) = TextyCard(
    elevationType = CardElevationType.SMALL,
    modifier = Modifier.padding(paddingMedium)
) {
    Row(
        modifier = Modifier.padding(paddingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Texty(text = title, textStyle = TextyStyle.CODE_LABEL.get())
        Spacer(modifier = Modifier.width(bigSpacer))
        content()
    }
}

@Composable
private fun LoadingItem(
    label: String,
    content: @Composable () -> Unit
) = Column(horizontalAlignment = Alignment.CenterHorizontally) {
    content()
    Spacer(modifier = Modifier.height(smallSpacer))
    Texty(text = label, textStyle = TextyStyle.CODE_LABEL.get())
}