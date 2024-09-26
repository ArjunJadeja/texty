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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardCornerRadius
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardElevationSmall
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingMedium
import com.arjunjadeja.texty.design_system.properties.AppDimens.sampleDemoCardSize
import com.arjunjadeja.texty.design_system.properties.AppDimens.smallSpacer

@Composable
fun BlinkingSample(isDemo: Boolean) = SampleCard(
    title = "Flash Sale",
    description = "Sample demonstrating blinking display styles in an e-commerce flash sale"
) {
    SampleWrappingBox(isDemo = isDemo) {
        Box(
            modifier = Modifier
                .size(sampleDemoCardSize)
                .background(Color(0xFFF5F5F5))
                .padding(paddingMedium)
        ) {
            Column {
                FlashSaleHeader()
                Spacer(modifier = Modifier.height(mediumSpacer))
                ProductCard(
                    name = "Premium Wireless Headphones",
                    discount = "50% OFF",
                    price = "$99.99",
                    originalPrice = "$199.99"
                )
                Spacer(modifier = Modifier.height(mediumSpacer))
                ProductCard(
                    name = "Smart Fitness Watch",
                    discount = "30% OFF",
                    price = "$69.99",
                    originalPrice = "$99.99",
                    isNew = true
                )
                Spacer(modifier = Modifier.height(mediumSpacer))
                ProductCard(
                    name = "Ultimate Coding Course",
                    discount = "30% OFF",
                    price = "$79.99",
                    originalPrice = "$99.99",
                    isNew = true
                )
            }
        }
    }
}

@Composable
private fun FlashSaleHeader() = Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = Color(0xFFFF4081)),
    shape = RoundedCornerShape(cardCornerRadius)
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        BlinkingText(
            text = "FLASH SALE",
            interval = 500L,
            color1 = Color.White,
            color2 = Color.Yellow,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
private fun ProductCard(
    name: String,
    discount: String,
    price: String,
    originalPrice: String,
    isNew: Boolean = false
) = Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    shape = RoundedCornerShape(cardCornerRadius),
    elevation = CardDefaults.cardElevation(defaultElevation = cardElevationSmall)
) {
    Column(modifier = Modifier.padding(paddingMedium)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Texty(
                text = name,
                color = { Color(0xFF333333) },
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            )
            if (isNew) {
                BlinkingText(
                    text = "NEW",
                    interval = 700L,
                    color1 = Color(0xFF4CAF50),
                    color2 = Color(0xFF81C784),
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Texty(
                    text = price,
                    color = { Color(0xFF4CAF50) },
                    textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BlinkingText(
                    text = discount,
                    interval = 300L,
                    color1 = Color(0xFFE91E63),
                    color2 = Color(0xFF9C27B0),
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
            Texty(
                text = originalPrice,
                color = { Color(0xFF9E9E9E) },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                )
            )
        }
    }
}

@Composable
private fun BlinkingText(
    text: String,
    interval: Long,
    color1: Color,
    color2: Color,
    style: TextStyle
) {
    var currentColor by remember { mutableStateOf(color1) }

    Box(modifier = Modifier.height(32.dp), contentAlignment = Alignment.Center) {
        Texty(
            text = text,
            displayStyle = DisplayStyle.Blinking(
                blinkInterval = interval,
                repeat = Repeat.Continuous,
                onBlink = {
                    currentColor = if (currentColor == color1) color2 else color1
                }
            ),
            color = { currentColor },
            textStyle = style
        )
    }
}