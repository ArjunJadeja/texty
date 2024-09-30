package com.arjunjadeja.texty.design_system.components.core

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ClickHandler
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun TextyCardTitle(title: String, onClick: ClickHandler) = Texty(
    text = title,
    textStyle = TextyStyle.DISPLAY_CARD_TITLE.get(),
    modifier = Modifier.clickable { onClick() }
)