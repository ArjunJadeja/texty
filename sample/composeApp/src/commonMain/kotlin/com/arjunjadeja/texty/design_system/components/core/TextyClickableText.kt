package com.arjunjadeja.texty.design_system.components.core

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ClickHandler
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun TextyClickableText(text: String, onClick: ClickHandler) = Texty(
    text = text,
    textStyle = TextyStyle.CLICKABLE_TEXT.get(),
    modifier = Modifier.clickable { onClick() }
)