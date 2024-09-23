package com.arjunjadeja.texty.design_system.components.core

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun TextyCardDescription(description: String) = Texty(
    text = description,
    textStyle = TextyStyle.DISPLAY_CARD_DESCRIPTION.get()
)