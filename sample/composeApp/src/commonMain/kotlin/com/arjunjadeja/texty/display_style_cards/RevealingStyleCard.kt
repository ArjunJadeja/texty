package com.arjunjadeja.texty.display_style_cards

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.RevealCover
import com.arjunjadeja.texty.RevealPattern
import com.arjunjadeja.texty.RevealType
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.design_system.components.DisplayStyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun RevealingStyleCard(displayStyle: DisplayStyle, onViewSampleClicked: DisplayStyleHandler) =
    TextyDisplayStyleCard(
        displayStyle = displayStyle,
        styleDescription = "A dynamic style that makes text blink with versatile options: blink once, infinitely, for a set time, or a specified count. The blink delay is customizable for added flexibility.",
        variations = listOf(
            DisplayStyleVariation(
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
            DisplayStyleVariation(
                name = "Revealing from end",
                code = """
                    Texty(
                        text = "Revealing Text",
                        displayStyle = DisplayStyle.Revealing(
                            revealPattern = RevealPattern.END_TO_START
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Revealing Text",
                        displayStyle = DisplayStyle.Revealing(
                            revealPattern = RevealPattern.END_TO_START
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            ),
            DisplayStyleVariation(
                name = "Revealing from centre to sides in 1 second",
                code = """
                    Texty(
                        text = "Revealing Text",
                        displayStyle = DisplayStyle.Revealing(
                            revealPattern = RevealPattern.CENTER_TO_SIDES,
                            revealType = RevealType.ByTotalTime(durationInMillis = 1000L)
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Revealing Text",
                        displayStyle = DisplayStyle.Revealing(
                            revealPattern = RevealPattern.CENTER_TO_SIDES,
                            revealType = RevealType.ByTotalTime(durationInMillis = 1000L)
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            ),
            DisplayStyleVariation(
                name = "Revealing from sides to centre with * as cover",
                code = """
                    Texty(
                        text = "Revealing Text",
                        displayStyle = DisplayStyle.Revealing(
                            revealPattern = RevealPattern.SIDES_TO_CENTER
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Revealing Text",
                        displayStyle = DisplayStyle.Revealing(
                            revealPattern = RevealPattern.SIDES_TO_CENTER,
                            revealCover = RevealCover.Custom(cover = "*")
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            )
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )