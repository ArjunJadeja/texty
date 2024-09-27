package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens
import com.arjunjadeja.texty.styles.pikachu_frame_eight
import com.arjunjadeja.texty.styles.pikachu_frame_five
import com.arjunjadeja.texty.styles.pikachu_frame_four
import com.arjunjadeja.texty.styles.pikachu_frame_one
import com.arjunjadeja.texty.styles.pikachu_frame_seven
import com.arjunjadeja.texty.styles.pikachu_frame_six
import com.arjunjadeja.texty.styles.pikachu_frame_three
import com.arjunjadeja.texty.styles.pikachu_frame_two

@Composable
fun MotionSample(isDemo: Boolean) = SampleCard(
    title = "Pikachu Animation",
    description = "Sample demonstrating Motion display style using a Pikachu animation."
) {
    SampleWrappingBox(isDemo = isDemo) {
        Column(
            modifier = Modifier
                .size(AppDimens.sampleDemoCardSize)
                .background(Color(0xFFFFEB3B))
                .padding(AppDimens.paddingMedium)
        ) {
            AnimationTitle()
            Spacer(modifier = Modifier.height(AppDimens.mediumSpacer))
            PikachuAnimation()
            Spacer(modifier = Modifier.height(AppDimens.mediumSpacer))
            AnimationControls()
        }
    }
}

@Composable
private fun AnimationTitle() = Card(
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1565C0)),
    shape = RoundedCornerShape(AppDimens.cardCornerRadius)
) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(AppDimens.paddingMedium),
        contentAlignment = Alignment.Center
    ) {
        Texty(
            text = "Pikachu Boxing",
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            color = { Color.White }
        )
    }
}

@Composable
private fun PikachuAnimation() {
    val pikachuFrames = listOf(
        pikachu_frame_one,
        pikachu_frame_two,
        pikachu_frame_three,
        pikachu_frame_four,
        pikachu_frame_five,
        pikachu_frame_six,
        pikachu_frame_seven,
        pikachu_frame_eight
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        shape = RoundedCornerShape(AppDimens.cardCornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = AppDimens.cardElevationSmall)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(180.dp).padding(AppDimens.paddingMedium),
            contentAlignment = Alignment.Center
        ) {
            Texty(
                textList = pikachuFrames,
                displayStyle = ListDisplayStyle.Motion(
                    delayBeforeNext = 80L,
                    repeat = Repeat.Continuous
                ),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 8.sp,
                    letterSpacing = 0.sp,
                    lineHeight = 8.sp
                ),
                color = { Color.Yellow }
            )
        }
    }
}

@Composable
private fun AnimationControls() = Card(
    colors = CardDefaults.cardColors(containerColor = Color.White),
    shape = RoundedCornerShape(AppDimens.cardCornerRadius),
    elevation = CardDefaults.cardElevation(defaultElevation = AppDimens.cardElevationSmall)
) {
    Column(modifier = Modifier.fillMaxWidth().padding(AppDimens.paddingMedium)) {
        Texty(
            text = "Motion Info",
            textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            color = { Color(0xFF333333) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Texty(
            text = "frames: 8",
            textStyle = TextStyle(fontSize = 14.sp),
            color = { Color(0xFF666666) }
        )
        Texty(
            text = "delay: 80ms per frame",
            textStyle = TextStyle(fontSize = 14.sp),
            color = { Color(0xFF666666) }
        )
    }
}