package com.arjunjadeja.texty.styles

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.StyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun BasicStyle(
    displayStyle: DisplayStyle,
    onViewSampleClicked: DisplayStyleHandler
) = StyleCard(
    title = displayStyle.toString(),
    description = "A clean, minimalist style that focuses on readability with simple, unadorned typography. Ideal for clear and straightforward text presentation.",
    variations = listOf(
        StyleVariation(
            name = "Default Basic Style",
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