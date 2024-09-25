package com.arjunjadeja.texty.ui.samples.normal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox

@Composable
fun SlidingSample(isDemo: Boolean) = SampleCard(
    title = "Sliding ASCII Patterns",
    description = "Sample demonstrating sliding display style using various strength patterns"
) {
    SampleWrappingBox(isDemo = isDemo) {
        val patterns = listOf(
            ">>>----->>>",
            "~~~~~~#####~~~~~~",
            ">>>>>-----<<<<<",
            "*****+++++*****",
            ">>====<<",
            "-*-*-*-*-*-*-*",
            "======####======",
            "~~~^^^~~~",
            "->->->->->->",
            "-==-==-==-=="
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(patterns) { pattern ->
                Texty(
                    text = pattern,
                    displayStyle = DisplayStyle.Sliding(
                        slidingDirection = SlidingDirection.TowardsEnd,
                        slideDuration = (2000..5000).random().toLong(),
                        repeat = Repeat.Continuous
                    ),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Center
                    ),
                    color = { Color.Magenta }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}