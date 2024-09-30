package com.arjunjadeja.texty.styles

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.RevealingCover
import com.arjunjadeja.texty.RevealingPattern
import com.arjunjadeja.texty.RevealingType
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.StyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun RevealingStyle(
    displayStyle: DisplayStyle,
    onViewSampleClicked: DisplayStyleHandler
) = StyleCard(
    title = displayStyle.toString(),
    description = "An engaging style where text is revealed character by character or by total time. Offers configurable options for reveal patterns, delays, and cover text, with a callback when the animation completes. Perfect for adding suspense or a gradual reveal effect.",
    variations = listOf(
        StyleVariation(
            name = "Default Revealing",
            code = """
                Texty(
                    text = "Revealing Text",
                    displayStyle = DisplayStyle.Revealing()
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    text = "Revealing Text",
                    displayStyle = DisplayStyle.Revealing(),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Revealing from end",
            code = """
                Texty(
                    text = "Revealing Text",
                    displayStyle = DisplayStyle.Revealing(
                        pattern = RevealingPattern.END_TO_START
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    text = "Revealing Text",
                    displayStyle = DisplayStyle.Revealing(pattern = RevealingPattern.END_TO_START),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Revealing from centre to sides in 1 second",
            code = """
                Texty(
                    text = "Revealing Text",
                    displayStyle = DisplayStyle.Revealing(
                        pattern = RevealingPattern.CENTER_TO_SIDES,
                        type = RevealingType.ByTotalTime(durationInMillis = 1000L)
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    text = "Revealing Text",
                    displayStyle = DisplayStyle.Revealing(
                        pattern = RevealingPattern.CENTER_TO_SIDES,
                        type = RevealingType.ByTotalTime(durationInMillis = 1000L)
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Revealing from sides to centre with * as cover",
            code = """
                Texty(
                    text = "Revealing Text",
                    displayStyle = DisplayStyle.Revealing(
                        pattern = RevealingPattern.SIDES_TO_CENTER,
                        cover = RevealingCover.Custom(cover = "*")
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    text = "Revealing Text",
                    displayStyle = DisplayStyle.Revealing(
                        pattern = RevealingPattern.SIDES_TO_CENTER,
                        cover = RevealingCover.Custom(cover = "*")
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        )
    ),
    onViewSampleClicked = { onViewSampleClicked(displayStyle) }
)