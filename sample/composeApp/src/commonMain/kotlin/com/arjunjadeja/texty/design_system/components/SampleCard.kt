package com.arjunjadeja.texty.design_system.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arjunjadeja.texty.design_system.components.core.CardElevationType
import com.arjunjadeja.texty.design_system.components.core.TextyCard
import com.arjunjadeja.texty.design_system.components.core.TextyCardDescription
import com.arjunjadeja.texty.design_system.components.core.TextyCardTitle
import com.arjunjadeja.texty.design_system.components.core.TextyClickableText
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig

@Composable
fun SampleCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TextyCard(
        modifier = modifier,
        elevationType = CardElevationType.MEDIUM
    ) {
        Column(modifier = Modifier.padding(all = paddingBig).animateContentSize()) {
            TextyCardTitle(title = title) { expanded = !expanded }
            Spacer(modifier = Modifier.height(height = mediumSpacer))
            TextyCardDescription(description = description)
            Spacer(modifier = Modifier.height(height = bigSpacer))
            if (expanded) content()
            else TextyClickableText(text = "Show More") { expanded = !expanded }
        }
    }
}