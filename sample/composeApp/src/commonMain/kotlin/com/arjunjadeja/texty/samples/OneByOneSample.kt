package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.arjunjadeja.texty.TransitionStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardCornerRadius

@Composable
fun OneByOneSample(isDemo: Boolean) = SampleCard(
    title = "Samachar News App",
    description = "Sample demonstrating OneByOne display style in a news app interface"
) {
    SampleWrappingBox(isDemo = isDemo) {
        Column(
            modifier = Modifier
                .size(AppDimens.sampleDemoCardSize)
                .background(Color.Black)
                .padding(12.dp)
        ) {
            SamacharAppBar()
            Spacer(modifier = Modifier.height(12.dp))
            NewsHeadlineCard(Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SamacharAppBar() = TopAppBar(
    title = {
        Texty(
            text = "Samachar",
            textStyle = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            ),
            color = { Color.White }
        )
    },
    actions = {
        IconButton(onClick = { /* no ops */ }) {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
        }
    },
    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
)

@Composable
private fun NewsHeadlineCard(modifier: Modifier = Modifier) {
    val headlines = listOf(
        "Global leaders meet in New York to discuss climate action, pledging to reduce emissions and promote green energy solutions.",
        "Tech giant introduces a new AI assistant that promises to transform daily tasks with its advanced intelligence.",
        "Scientists reveal a potential breakthrough in the fight against the common cold, offering hope for a future cure.",
        "Stock market hits a new high as investors respond to positive economic data and strong quarterly earnings.",
        "A major leap in renewable energy as a new solar panel design significantly boosts efficiency and reduces costs.",
        "The International Space Station marks 25 years in orbit, celebrating decades of scientific achievement and exploration.",
        "New research suggests that coffee consumption may lower the risk of heart disease and improve cognitive function."
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(cardCornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier.fillMaxWidth().padding(12.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Texty(
                text = "Top News",
                textStyle = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 26.sp,
                    letterSpacing = 0.5.sp
                ),
                color = { Color.DarkGray }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier.fillMaxSize().background(Color.LightGray.copy(alpha = 0.1f)),
                contentAlignment = Alignment.CenterStart
            ) {
                Texty(
                    textList = headlines,
                    displayStyle = ListDisplayStyle.OneByOne(
                        transitionStyle = TransitionStyle.TYPING,
                        displayDuration = 2000L,
                        transitionInDuration = 2000L,
                        transitionOutDuration = 2000L,
                        repeat = Repeat.Continuous
                    ),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 28.sp,
                        letterSpacing = 0.2.sp
                    ),
                    color = { Color.Black }
                )
            }
        }
    }
}