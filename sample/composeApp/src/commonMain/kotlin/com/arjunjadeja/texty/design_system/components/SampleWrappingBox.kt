package com.arjunjadeja.texty.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arjunjadeja.texty.design_system.properties.AppDimens.desktopSampleAspectRatio
import com.arjunjadeja.texty.design_system.properties.AppDimens.mobileSampleAspectRatio
import com.arjunjadeja.texty.design_system.properties.AppDimens.sampleDemoCardSize
import com.arjunjadeja.texty.design_system.theme.isDesktop

@Composable
fun SampleWrappingBox(
    isDemo: Boolean = false,
    content: @Composable () -> Unit
) = Box(
    modifier = Modifier
        .then(
            if (isDemo) Modifier
                .size(sampleDemoCardSize)
                .background(Color.White)
            else if (isDesktop()) Modifier
                .fillMaxWidth()
                .aspectRatio(desktopSampleAspectRatio)
            else Modifier
                .fillMaxWidth()
                .aspectRatio(mobileSampleAspectRatio)
        ),
    contentAlignment = Alignment.Center
) { content() }