package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox

@Composable
fun BasicSample(isDemo: Boolean) = SampleCard(
    title = "Neon Sign Generator",
    description = "Sample demonstrating Basic display style with a neon sign generator"
) {
    SampleWrappingBox(isDemo = isDemo) {
        NeonSignGenerator()
    }
}

@Composable
private fun NeonSignGenerator() {
    var signText by remember { mutableStateOf("OPEN") }
    var textColor by remember { mutableStateOf(Color(0xFF00FF00)) } // Neon green

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(textColor.copy(alpha = 0.2f), Color.Transparent),
                        center = Offset.Infinite,
                        radius = 300f
                    )
                )
                .border(
                    width = 4.dp,
                    brush = Brush.linearGradient(listOf(textColor, textColor.copy(alpha = 0.5f))),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Texty(
                text = signText,
                displayStyle = DisplayStyle.Basic(),
                color = { textColor },
                textStyle = TextStyle(
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    shadow = Shadow(
                        color = textColor.copy(alpha = 0.7f),
                        offset = Offset(0f, 0f),
                        blurRadius = 15f
                    )
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = signText,
            onValueChange = { signText = it.uppercase().take(10) },
            label = {
                Texty(
                    text = "Enter sign text (max 10 chars)",
                    displayStyle = DisplayStyle.Basic(),
                    color = { Color.White },
                    textStyle = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
                )
            },
            textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF2A2A2A),
                unfocusedContainerColor = Color(0xFF2A2A2A)
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ColorButton(Color(0xFF00FF00)) { textColor = it } // Neon Green
            ColorButton(Color(0xFFFF00FF)) { textColor = it } // Neon Pink
            ColorButton(Color(0xFF00FFFF)) { textColor = it } // Neon Blue
            ColorButton(Color(0xFFFFFF00)) { textColor = it } // Neon Yellow
        }
    }
}

@Composable
private fun ColorButton(color: Color, onClick: (Color) -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(color, shape = CircleShape)
            .border(2.dp, Color.White, shape = CircleShape)
            .clickable { onClick(color) }
    )
}