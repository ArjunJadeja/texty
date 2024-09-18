package com.arjunjadeja.texty.design_system.properties

import androidx.compose.ui.unit.dp
import com.arjunjadeja.texty.design_system.theme.isDesktop

object AppDimens {
    val maxWidth = 800.dp
    val borderStrokeWidth = 1.dp

    val paddingSmall = if (isDesktop()) 8.dp else 6.dp
    val paddingMedium = if (isDesktop()) 16.dp else 12.dp
    val paddingBig = if (isDesktop()) 32.dp else 24.dp

    val smallSpacer = if (isDesktop()) 8.dp else 6.dp
    val mediumSpacer = if (isDesktop()) 16.dp else 12.dp
    val bigSpacer = if (isDesktop()) 32.dp else 24.dp

    val minCardHeight = if (isDesktop()) 64.dp else 48.dp
    val displayStyleCardMinHeight = if (isDesktop()) 128.dp else 96.dp

    val cardCornerRadius = 16.dp
    val squareCardCornerRadius = 0.dp

    val cardElevationNil = 0.dp
    val cardElevationSmall = if (isDesktop()) 8.dp else 6.dp
    val cardElevationMedium = if (isDesktop()) 16.dp else 12.dp
    val cardElevationBig = if (isDesktop()) 24.dp else 18.dp
}
