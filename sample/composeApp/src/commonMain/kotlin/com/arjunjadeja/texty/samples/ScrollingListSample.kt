package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.ScrollingDirection
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer

@Composable
fun ScrollingListSample(isDemo: Boolean) = SampleCard(
    title = "Inspiration Wall",
    description = "Sample demonstrating Scrolling List display style using inspiration wall."
) {
    SampleWrappingBox(isDemo = isDemo) {
        Column(
            modifier = Modifier
                .size(AppDimens.sampleDemoCardSize)
                .background(Color(0xFFF5F5F5))
                .padding(AppDimens.paddingMedium)
        ) {
            WallHeader()
            Spacer(modifier = Modifier.height(mediumSpacer))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InspirationQuotes(modifier = Modifier.weight(1f))
                DailyGoals(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(mediumSpacer))
            AchievementBadges()
        }
    }
}

@Composable
private fun WallHeader() = Texty(
    text = "My Inspiration Wall",
    textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    color = { Color(0xFF333333) }
)

@Composable
private fun InspirationQuotes(modifier: Modifier = Modifier) {
    val quotes = listOf(
        "Believe you can and you're halfway there.",
        "The future belongs to those who believe in the beauty of their dreams.",
        "Success is not final, failure is not fatal: it is the courage to continue that counts.",
        "The only way to do great work is to love what you do.",
        "Strive not to be a success, but rather to be of value."
    )

    ScrollingCard(
        title = "Daily Inspiration",
        content = quotes,
        backgroundColor = Color(0xFFE8F5E9),
        contentColor = Color(0xFF2E7D32),
        modifier = modifier
    )
}

@Composable
private fun DailyGoals(modifier: Modifier = Modifier) {
    val goals = listOf(
        "Meditate for 10 minutes",
        "Read 20 pages",
        "Write in journal",
        "Exercise for 30 minutes",
        "Learn something new",
        "Connect with a friend",
        "Practice gratitude"
    )

    ScrollingCard(
        title = "Today's Goals",
        content = goals,
        backgroundColor = Color(0xFFE3F2FD),
        contentColor = Color(0xFF1565C0),
        modifier = modifier
    )
}

@Composable
private fun AchievementBadges() {
    val badges = listOf(
        "🏆 30-Day Streak",
        "📚 Bookworm",
        "💪 Fitness Enthusiast",
        "🧘 Mindfulness Master",
        "🌟 Goal Crusher",
        "🚀 Productivity Pro"
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
        shape = RoundedCornerShape(AppDimens.cardCornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = AppDimens.cardElevationSmall),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(AppDimens.paddingMedium)) {
            Texty(
                text = "Achievement Badges",
                textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                color = { Color(0xFFE65100) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Texty(
                textList = badges,
                displayStyle = ListDisplayStyle.ScrollingList(
                    spacing = 16.dp,
                    direction = ScrollingDirection.TOWARDS_TOP,
                    duration = 15000L,
                    repeat = Repeat.Continuous
                ),
                modifier = Modifier.fillMaxWidth().height(48.dp),
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                color = { Color(0xFFF57C00) }
            )
        }
    }
}

@Composable
private fun ScrollingCard(
    title: String,
    content: List<String>,
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) = Card(
    colors = CardDefaults.cardColors(containerColor = backgroundColor),
    shape = RoundedCornerShape(AppDimens.cardCornerRadius),
    elevation = CardDefaults.cardElevation(defaultElevation = AppDimens.cardElevationSmall),
    modifier = modifier
) {
    Column(modifier = Modifier.padding(AppDimens.paddingMedium)) {
        Texty(
            text = title,
            textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            color = { contentColor }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Texty(
            textList = content,
            displayStyle = ListDisplayStyle.ScrollingList(
                spacing = 16.dp,
                direction = ScrollingDirection.TOWARDS_TOP,
                duration = 10000L,
                repeat = Repeat.Continuous
            ),
            modifier = Modifier.fillMaxWidth().height(130.dp),
            textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
            color = { contentColor }
        )
    }
}