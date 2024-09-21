package com.arjunjadeja.texty.display_style_cards

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun TypingStyleCard(displayStyle: DisplayStyle, onViewSampleClicked: DisplayStyleHandler) {
    TextyDisplayStyleCard(
        title = displayStyle.toString(),
        description = "A typewriter-inspired style that mimics the appearance of text being typed. Ideal for creating a dynamic, real-time effect.",
        variations = listOf(
            StyleVariation(
                name = "Default Typing",
                code = """
                    Texty(
                        text = "Typing Text",
                        displayStyle = DisplayStyle.Typing()
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Typing Text",
                        displayStyle = DisplayStyle.Typing(),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            ),
            StyleVariation(
                name = "Custom Typing Delay",
                code = """
                    Texty(
                        text = "Typing Text",
                        displayStyle = DisplayStyle.Typing(typingDelayPerChar = 800L)
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Typing Text",
                        displayStyle = DisplayStyle.Typing(typingDelayPerChar = 800L),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            )
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )
}