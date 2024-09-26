package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.TransitionDirection
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens.sampleDemoCardSize

@Composable
fun StickAndRevealSample(isDemo: Boolean) = SampleCard(
    title = "ASCII Art Reveal",
    description = "Sample demonstrating stick and reveal display style using ASCII art."
) {
    val frame by remember {
        mutableStateOf(
            """
            ⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⠇⠀⠀
            ⠀⢻⣿⠒⠤⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⠊⠸⡟⠀⠀⠀
            ⠀⠀⠻⡀⠀⠀⠑⠢⠠⠄⠀⠀⠀⠠⠄⡰⠃⠀⢀⡜⠀⠀⠀⣀
            ⠀⠀⠀⠈⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣠⠊⠀⢀⠄⠈⢸
            ⠀⠀⠀⠀⠀⡜⠁⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⢸⠠⠊⠁⠀⠀⢸
            ⠀⠀⠀⠀⢀⠃⠀⠀⢿⣾⠃⠀⠀⠀⠐⣽⡇⢸⠀⠀⠀⠀⢀⡸
            ⠀⠀⠀⠀⣨⣀⠮⠷⢤⡀⠀⢀⠀⠁⠀⠀⡠⢲⠆⠀⠐⠈⠁⠀
            ⠀⠀⢀⠞⡱⠒⠀⡤⠄⠘⡆⠀⠁⠉⠀⠀⣑⣚⡀⠀⠀⠀⠀⠀
            ⠀⠀⢸⠾⡍⡀⠀⠀⣋⣤⡟⠄⠀⠄⢀⡎⡅⠀⠈⠳⡄⠀⠀⠀
            ⠀⠀⠈⢷⣶⣶⣶⣿⣿⣿⠁⠀⠀⠀⣼⣽⣦⡀⠀⠀⢹⠀⠀⠀
            ⠀⠀⠀⢀⠃⠀⠈⠉⠉⠀⠀⠀⠀⠀⠈⠛⠿⣿⣿⣶⠟⠀⠀⠀
            ⠀⠀⠀⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡅⠀⠀⠀⠀⠀
            ⠀⠀⠀⠐⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⢐⣁⠵⠒⠒⠒⠒⠒⠒⠠⠤⡀⠀⡔⠁⠀⠀⠀⠀⠀⠀
            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠄⠵⠀⠀⠀⠀⠀⠀⠀
            """.trimIndent()
        )
    }

    val cover by remember {
        mutableStateOf("█")
    }

    SampleWrappingBox(isDemo = isDemo) {
        Box(
            modifier = Modifier
                .size(sampleDemoCardSize)
                .background(Color(0xFF121212))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Texty(
                text = frame,
                displayStyle = DisplayStyle.StickAndReveal(
                    stickingDelay = 40L,
                    revealingDelay = 30L,
                    delayBeforeReveal = 500L,
                    stickingDirection = TransitionDirection.TOP_TO_BOTTOM,
                    revealingDirection = TransitionDirection.BOTTOM_TO_TOP,
                    cover = cover
                ),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center
                ),
                color = { Color(0xFFFBD13B) }
            )
        }
    }
}