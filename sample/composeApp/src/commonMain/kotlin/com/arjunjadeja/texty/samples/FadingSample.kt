package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.FadingType
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardCornerRadiusBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardElevationSmall
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingMedium
import com.arjunjadeja.texty.design_system.properties.AppDimens.sampleDemoCardSize

data class Quote(val text: String, val author: String)

@Composable
fun FadingSample(isDemo: Boolean) = SampleCard(
    title = "Quote of the Day",
    description = "Sample demonstrating fading display styles as a quote."
) {
    SampleWrappingBox(isDemo = isDemo) {
        QuoteOfTheDay()
    }
}

@Composable
private fun QuoteOfTheDay() {
    val quotes = listOf(
        Quote("Stay hungry, Stay foolish", "Steve Jobs"),
        Quote("Never Settle", "Also him and OnePlus")
    )

    var currentQuoteIndex by remember { mutableStateOf(0) }
    var isFadingIn by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .size(sampleDemoCardSize)
            .background(Color(0xFFE3F2FD)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxSize().padding(paddingBig),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(cardCornerRadiusBig),
            elevation = CardDefaults.cardElevation(defaultElevation = cardElevationSmall)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingMedium),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Texty(
                    text = "Quote of the Day",
                    color = { Color(0xFF1976D2) },
                    textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(bigSpacer))
                FadingQuote(
                    quote = quotes[currentQuoteIndex],
                    isFadingIn = isFadingIn,
                    onFadeComplete = {
                        if (!isFadingIn) currentQuoteIndex = (currentQuoteIndex + 1) % quotes.size
                        isFadingIn = !isFadingIn
                    }
                )
            }
        }
    }
}

@Composable
private fun FadingQuote(
    quote: Quote,
    isFadingIn: Boolean,
    onFadeComplete: () -> Unit
) = Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Texty(
        text = quote.text,
        displayStyle = DisplayStyle.Fading(
            type = if (isFadingIn) FadingType.IN else FadingType.OUT,
            duration = 2000L,
            onComplete = onFadeComplete
        ),
        color = { Color(0xFF37474F) },
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(mediumSpacer))
    Texty(
        text = "- ${quote.author}",
        displayStyle = DisplayStyle.Fading(
            type = if (isFadingIn) FadingType.IN else FadingType.OUT,
            duration = 2000L
        ),
        color = { Color(0xFF546E7A) },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
        )
    )
}