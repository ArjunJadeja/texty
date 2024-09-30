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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer

@Composable
fun SlidingListSample(isDemo: Boolean) = SampleCard(
    title = "Digital Classroom",
    description = "Sample demonstrating Sliding List display styles in a digital classroom."
) {
    SampleWrappingBox(isDemo = isDemo) {
        Column(
            modifier = Modifier
                .size(AppDimens.sampleDemoCardSize)
                .background(Color(0xFFF5F5F5))
                .padding(AppDimens.paddingMedium)
        ) {
            ClassroomAppBar()
            Spacer(modifier = Modifier.height(mediumSpacer))
            PresentStudentsList()
            Spacer(modifier = Modifier.height(mediumSpacer))
            ClassroomMetrics()
            Spacer(modifier = Modifier.height(mediumSpacer))
            CurrentTopicCard()
        }
    }
}

@Composable
private fun ClassroomAppBar() = Card(
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1565C0)),
    shape = RoundedCornerShape(AppDimens.cardCornerRadius)
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(AppDimens.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Texty(
            text = "Digital Classroom",
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            color = { Color.White }
        )
        Texty(
            text = "CS50",
            textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
            color = { Color.White }
        )
    }
}

@Composable
private fun PresentStudentsList() {
    val students = listOf(
        "Alice Johnson", "Bob Smith", "Charlie Brown", "Diana Prince",
        "Ethan Hunt", "Fiona Apple", "George Lucas", "Hannah Montana"
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(AppDimens.cardCornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = AppDimens.cardElevationSmall)
    ) {
        Column(modifier = Modifier.padding(AppDimens.paddingMedium)) {
            Texty(
                text = "Present Students",
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = { Color(0xFF333333) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Texty(
                textList = students,
                displayStyle = ListDisplayStyle.SlidingList(
                    separator = " <-> ",
                    direction = SlidingDirection.TOWARDS_START,
                    duration = 15000L,
                    repeat = Repeat.Continuous
                ),
                modifier = Modifier.fillMaxWidth().height(32.dp),
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
                maxLines = 1,
                color = { Color(0xFF4CAF50) }
            )
        }
    }
}

@Composable
private fun ClassroomMetrics() {
    val metrics = listOf(
        "Questions Asked: 15", "Answers Given: 42", "Quiz Scores: 85% avg",
        "Engagement Rate: 92%", "Time Remaining: 45 mins"
    )

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE1F5FE)),
        shape = RoundedCornerShape(AppDimens.cardCornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = AppDimens.cardElevationSmall)
    ) {
        Column(modifier = Modifier.padding(AppDimens.paddingMedium)) {
            Texty(
                text = "Class Metrics",
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = { Color(0xFF0277BD) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Texty(
                textList = metrics,
                displayStyle = ListDisplayStyle.SlidingList(
                    separator = " | ",
                    direction = SlidingDirection.TOWARDS_END,
                    duration = 10000L,
                    repeat = Repeat.Continuous
                ),
                modifier = Modifier.fillMaxWidth().height(32.dp),
                textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
                color = { Color(0xFF01579B) }
            )
        }
    }
}

@Composable
private fun CurrentTopicCard() = Card(
    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
    shape = RoundedCornerShape(AppDimens.cardCornerRadius),
    elevation = CardDefaults.cardElevation(defaultElevation = AppDimens.cardElevationSmall)
) {
    val topics = listOf(
        "Introduction to Algorithms", "Data Structures", "Time Complexity",
        "Space Complexity", "Sorting Algorithms", "Searching Algorithms"
    )

    Column(modifier = Modifier.padding(AppDimens.paddingMedium)) {
        Texty(
            text = "Current Topic",
            textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            color = { Color(0xFFE65100) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Texty(
            textList = topics,
            displayStyle = ListDisplayStyle.SlidingList(
                separator = " >> ",
                direction = SlidingDirection.TOWARDS_START,
                duration = 8000L,
                repeat = Repeat.Continuous
            ),
            textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
            maxLines = 1,
            color = { Color(0xFFF57C00) }
        )
    }
}