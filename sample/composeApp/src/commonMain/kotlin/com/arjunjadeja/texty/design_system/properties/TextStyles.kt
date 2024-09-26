package com.arjunjadeja.texty.design_system.properties

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

enum class TextyStyle {
    TOP_BAR_TITLE,
    CLICKABLE_TEXT,
    DISPLAY_STYLE_DEMO_TEXT,
    DISPLAY_STYLE_SELECTED_TAB,
    DISPLAY_STYLE_TAB,
    DISPLAY_STYLE_TAB_NUMBER,
    CODE_LABEL,
    COPY_BUTTON_STYLE,
    DISPLAY_STYLE_VARIATION_NAME,
    DISPLAY_CARD_TITLE,
    DISPLAY_CARD_DESCRIPTION,
    DISPLAY_CARD_BUTTON_TEXT,
    FOOTER
}

@Composable
fun TextyStyle.get() = when (this) {

    TextyStyle.TOP_BAR_TITLE -> MaterialTheme.typography.titleLarge.copy(
        color = MaterialTheme.colorScheme.onBackground
    )

    TextyStyle.CLICKABLE_TEXT -> MaterialTheme.typography.bodyMedium.copy(
        brush = Brush.linearGradient(colors = Gradients.textGradient)
    )

    TextyStyle.DISPLAY_STYLE_SELECTED_TAB -> MaterialTheme.typography.labelLarge.copy(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )
    TextyStyle.DISPLAY_STYLE_TAB -> MaterialTheme.typography.labelLarge.copy(
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Normal
    )

    TextyStyle.DISPLAY_STYLE_TAB_NUMBER -> MaterialTheme.typography.labelMedium.copy(
        fontWeight = FontWeight.Bold
    )

    TextyStyle.DISPLAY_STYLE_DEMO_TEXT -> MaterialTheme.typography.displayMedium.copy(
        color = MaterialTheme.colorScheme.onBackground
    )

    TextyStyle.CODE_LABEL -> TextStyle(
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    TextyStyle.COPY_BUTTON_STYLE -> TextStyle(color = MaterialTheme.colorScheme.onBackground)

    TextyStyle.DISPLAY_STYLE_VARIATION_NAME -> MaterialTheme.typography.titleLarge.copy(
        color = MaterialTheme.colorScheme.onBackground
    )

    TextyStyle.DISPLAY_CARD_TITLE -> MaterialTheme.typography.displayMedium.copy(
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Black
    )

    TextyStyle.DISPLAY_CARD_DESCRIPTION -> MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
    )

    TextyStyle.DISPLAY_CARD_BUTTON_TEXT -> MaterialTheme.typography.labelMedium.copy(
        color = MaterialTheme.colorScheme.onPrimary
    )

    TextyStyle.FOOTER -> MaterialTheme.typography.labelSmall.copy(
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        fontWeight = FontWeight.Light
    )
}
