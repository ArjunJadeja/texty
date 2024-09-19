package com.arjunjadeja.texty.display_style_cards

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.FadingType
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.design_system.components.DisplayStyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun FadingStyleCard(displayStyle: DisplayStyle, onViewSampleClicked: DisplayStyleHandler) =
    TextyDisplayStyleCard(
        displayStyle = displayStyle,
        styleDescription = "A smooth transition style where text fades in and out. Configurable options include fade duration and fade type, with a callback when the fade completes, allowing for seamless integration into various animations.",
        variations = listOf(
            DisplayStyleVariation(
                name = "Fading In",
                code = """
                    Texty(        
                        text = "Text Fading In",
                        displayStyle = DisplayStyle.Fading(
                            type = FadingType.IN,
                            durationInMillis = 2000L
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Text Fading In",
                        displayStyle = DisplayStyle.Fading(
                            type = FadingType.IN,
                            durationInMillis = 2000L
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            ),
            DisplayStyleVariation(
                name = "Fading Out",
                code = """
                    Texty(        
                        text = "Text Fading Out",
                        displayStyle = DisplayStyle.Fading(
                            type = FadingType.OUT,
                            durationInMillis = 2000L
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Text Fading Out",
                        displayStyle = DisplayStyle.Fading(
                            type = FadingType.OUT,
                            durationInMillis = 2000L
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            ),
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )