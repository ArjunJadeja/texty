package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.RevealingPattern
import com.arjunjadeja.texty.RevealingType
import com.arjunjadeja.texty.RevealingCover
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.sampleDemoCardSize

data class WordOfTheDay(
    val word: String,
    val partOfSpeech: String,
    val definition: String,
    val example: String
)

@Composable
fun RevealingSample(isDemo: Boolean) = SampleCard(
    title = "Word of the Day",
    description = "Sample demonstrating revealing display style using a word of the day feature."
) {
    val wordOfTheDay = WordOfTheDay(
        word = "Serendipity",
        partOfSpeech = "noun",
        definition = "The occurrence and development of events by chance in a happy or beneficial way.",
        example = "A fortunate stroke of serendipity brought them together."
    )

    SampleWrappingBox(isDemo = isDemo) {
        Column(
            modifier = Modifier
                .size(sampleDemoCardSize)
                .background(Color(0xFFFFF5D8))
                .padding(paddingBig),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Texty(
                text = "Word of the Day",
                color = { Color(0xFF1A237E) },
                textStyle = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(mediumSpacer))
            Texty(
                text = wordOfTheDay.word,
                displayStyle = DisplayStyle.Revealing(
                    delayBeforeRevealing = 500L,
                    pattern = RevealingPattern.CENTER_TO_SIDES,
                    type = RevealingType.ByEachCharacter(delayInMillis = 200L)
                ),
                color = { Color(0xFF3F51B5) },
                textStyle = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(mediumSpacer))
            Texty(
                text = wordOfTheDay.partOfSpeech,
                displayStyle = DisplayStyle.Revealing(
                    delayBeforeRevealing = 1000L,
                    pattern = RevealingPattern.START_TO_END,
                    type = RevealingType.ByTotalTime(durationInMillis = 1000L)
                ),
                color = { Color(0xFF7986CB) },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Spacer(modifier = Modifier.height(mediumSpacer))
            Texty(
                text = "Definition:",
                color = { Color(0xFF303F9F) },
                textStyle = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(mediumSpacer))
            Texty(
                text = wordOfTheDay.definition,
                displayStyle = DisplayStyle.Revealing(
                    pattern = RevealingPattern.START_TO_END,
                    type = RevealingType.ByEachCharacter(delayInMillis = 30L),
                    cover = RevealingCover.Custom("_")
                ),
                color = { Color(0xFF5C6BC0) },
                textStyle = TextStyle(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(mediumSpacer))
            Texty(
                text = "Example:",
                color = { Color(0xFF303F9F) },
                textStyle = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(mediumSpacer))
            Texty(
                text = wordOfTheDay.example,
                displayStyle = DisplayStyle.Revealing(
                    pattern = RevealingPattern.END_TO_START,
                    type = RevealingType.ByTotalTime(durationInMillis = 2000L)
                ),
                color = { Color(0xFF5C6BC0) },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}