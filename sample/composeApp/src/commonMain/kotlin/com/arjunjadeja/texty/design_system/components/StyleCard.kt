package com.arjunjadeja.texty.design_system.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.arjunjadeja.texty.design_system.properties.AppDimens.borderStrokeWidth
import com.arjunjadeja.texty.design_system.properties.AppDimens.mediumSpacer
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingSmall
import com.arjunjadeja.texty.design_system.properties.AppDimens.smallCardRadius
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
    var selectedVariation by remember { mutableStateOf(0) }

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
                VariationTabs(variations, selectedVariation) { selectedVariation = it }
                Spacer(modifier = Modifier.height(height = mediumSpacer))
                ShowVariationContent(variation = variations[selectedVariation])
                Spacer(modifier = Modifier.height(height = bigSpacer))
                TextyClickableText(text = "View Samples") { onViewSampleClicked() }
            } else TextyClickableText(text = "Show More") { expanded = !expanded }
        }
    }
}

@Composable
private fun VariationTabs(
    variations: List<DisplayStyleVariation>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) = LazyRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Start
) {
    items(variations.size) { index ->
        Column(
            modifier = Modifier
                .padding(end = mediumSpacer)
                .clickable { onSelect(index) }
                .border(
                    width = borderStrokeWidth,
                    color = if (index == selectedIndex) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(smallCardRadius)
                )
                .background(
                    color = if (index == selectedIndex) MaterialTheme.colorScheme.primary
                    else Color.Transparent,
                    shape = RoundedCornerShape(smallCardRadius)
                )
                .padding(paddingSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Texty(
                text = "${index + 1}",
                textStyle = TextyStyle.DISPLAY_STYLE_TAB_NUMBER.get().copy(
                    color = if (index == selectedIndex) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
            )
        }
    }
}

@Composable
private fun ShowVariationContent(variation: DisplayStyleVariation) {
    var showDemo by remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.height(height = mediumSpacer))
    VariationName(name = variation.name)
    Spacer(modifier = Modifier.height(height = mediumSpacer))
    DemoCard(showDemo = showDemo, demoContent = variation.demoContent)
    Spacer(modifier = Modifier.height(height = bigSpacer))
    DemoButton(showDemo = showDemo) { showDemo = !showDemo }
    Spacer(modifier = Modifier.height(height = bigSpacer))
    DemoCodeBlock(code = variation.code)
}

@Composable
private fun VariationName(name: String) = Texty(
    text = name, textStyle = TextyStyle.DISPLAY_STYLE_VARIATION_NAME.get()
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
