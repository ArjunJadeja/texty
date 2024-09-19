package com.arjunjadeja.texty.display_style_cards

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.design_system.components.DisplayStyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun BasicStyleCard(displayStyle: DisplayStyle, onViewSampleClicked: DisplayStyleHandler) {
    TextyDisplayStyleCard(
        displayStyle = displayStyle,
        styleDescription = "A clean, minimalist style that focuses on readability with simple, unadorned typography. Ideal for clear and straightforward text presentation.",
        variations = listOf(
            DisplayStyleVariation(
                name = "Basic",
                code = """
                    Texty(text = "Basic Text")
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Basic Text",
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            )
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )
}