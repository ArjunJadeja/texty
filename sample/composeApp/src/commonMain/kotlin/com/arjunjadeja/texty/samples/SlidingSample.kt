package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardElevationSmall
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingMedium
import com.arjunjadeja.texty.design_system.properties.AppDimens.smallSpacer

data class AppSection(val name: String, val iconText: String)

@Composable
fun SlidingSample(isDemo: Boolean) = SampleCard(
    title = "Super App ",
    description = "Sample demonstrating sliding display styles in a super app."
) {
    SampleWrappingBox(isDemo = isDemo) {
        SuperAppInterface()
    }
}

@Composable
private fun SuperAppInterface() {
    val sections = listOf(
        AppSection("Messages", "💬"),
        AppSection("Payments", "💳"),
        AppSection("Services", "🚗"),
        AppSection("Discover", "🔍"),
        AppSection("Games", "🎮"),
        AppSection("News", "📰")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(paddingMedium)
    ) {
        SuperAppBar()
        Spacer(modifier = Modifier.height(smallSpacer))
        SliderCard(
            message = "Special Offer: Get 10% off on all services today!",
            direction = SlidingDirection.TowardsStart
        )
        Spacer(modifier = Modifier.height(smallSpacer))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(smallSpacer),
            verticalArrangement = Arrangement.spacedBy(smallSpacer),
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            items(sections) { section ->
                AppSectionCard(section)
            }
        }
        Spacer(modifier = Modifier.height(smallSpacer))
        SliderCard(
            message = "Tech giant announces new smartphone launch!",
            direction = SlidingDirection.TowardsEnd
        )
    }
}

@Composable
private fun SuperAppBar() = Card(
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1AAD19)),
    shape = RoundedCornerShape(8.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = cardElevationSmall)
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(paddingMedium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Texty(
            text = "SuperApp",
            color = { Color.White },
            textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        Texty(
            text = "•••",
            color = { Color.White },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
private fun AppSectionCard(section: AppSection) = Card(
    colors = CardDefaults.cardColors(containerColor = Color.White),
    shape = RoundedCornerShape(8.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = cardElevationSmall),
    modifier = Modifier.aspectRatio(1.2f).padding(4.dp)
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Texty(
            text = section.iconText,
            textStyle = TextStyle(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Texty(
            text = section.name,
            color = { Color(0xFF333333) },
            textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
private fun SliderCard(message: String, direction: SlidingDirection) = Card(
    colors = CardDefaults.cardColors(containerColor = Color.White),
    shape = RoundedCornerShape(8.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
) {
    Box(
        modifier = Modifier.fillMaxWidth().height(40.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Texty(
            text = message,
            displayStyle = DisplayStyle.Sliding(
                slidingDirection = direction,
                slideDuration = 12_000L,
                repeat = Repeat.Continuous
            ),
            textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
            color = { Color.Black }
        )
    }
}