package com.arjunjadeja.texty.design_system.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ClickHandler
import com.arjunjadeja.texty.base.DisplayStyleVariation
import com.arjunjadeja.texty.design_system.components.core.CardElevationType
import com.arjunjadeja.texty.design_system.components.core.TextyCard
import com.arjunjadeja.texty.design_system.components.core.TextyCardDescription
import com.arjunjadeja.texty.design_system.components.core.TextyCardTitle
import com.arjunjadeja.texty.design_system.components.core.TextyClickableText
import com.arjunjadeja.texty.design_system.properties.AppDimens.displayStyleCardMinHeight
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.bigSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.squareCardCornerRadius
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun StyleCard(
    title: String,
    description: String,
    variations: List<DisplayStyleVariation>,
    onViewSampleClicked: () -> Unit,
    modifier: Modifier = Modifier
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
            if (expanded) {
                ShowStyleVariations(variations)
                TextyClickableText(text = "View Samples") { onViewSampleClicked() }
            } else {
                TextyClickableText(text = "Show More") { expanded = !expanded }
            }
        }
    }
}

@Composable
private fun ShowStyleVariations(variations: List<DisplayStyleVariation>) = variations
    .forEachIndexed { index, variation ->
        var showDemo by remember { mutableStateOf(false) }
        if (variations.size > 1) {
            VariationName(index = index, name = variation.name)
            Spacer(modifier = Modifier.height(height = mediumSpacer))
        }
        DemoCard(showDemo = showDemo, demoContent = variation.demoContent)
        Spacer(modifier = Modifier.height(height = bigSpacer))
        DemoButton(showDemo = showDemo) { showDemo = !showDemo }
        Spacer(modifier = Modifier.height(height = bigSpacer))
        DemoCodeBlock(code = variation.code)
        Spacer(modifier = Modifier.height(height = bigSpacer))
    }

@Composable
private fun VariationName(index: Int, name: String) = Texty(
    text = "${('a' + index).lowercase()}. $name",
    textStyle = TextyStyle.DISPLAY_STYLE_VARIATION_NAME.get()
)

@Composable
private fun DemoCard(showDemo: Boolean, demoContent: @Composable () -> Unit) = TextyCard(
    elevationType = CardElevationType.NIL,
    cornerRadius = squareCardCornerRadius,
    showBorder = true
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = displayStyleCardMinHeight)
            .background(MaterialTheme.colorScheme.surface)
    ) { if (showDemo) demoContent() }
}

@Composable
private fun DemoButton(showDemo: Boolean, onClick: ClickHandler) = Row(
    horizontalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxWidth()
) {
    Button(onClick = { onClick() }) {
        Texty(
            text = if (showDemo) "Hide Demo" else "Show Demo",
            textStyle = TextyStyle.DISPLAY_CARD_BUTTON_TEXT.get()
        )
    }
}

@Composable
private fun DemoCodeBlock(code: String) = TextyCard(
    elevationType = CardElevationType.NIL,
    showBorder = true
) { CopyableCodeBlock(code = code, modifier = Modifier.fillMaxWidth()) }
