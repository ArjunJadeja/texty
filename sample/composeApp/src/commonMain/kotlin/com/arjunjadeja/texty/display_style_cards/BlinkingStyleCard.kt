package com.arjunjadeja.texty.display_style_cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun BlinkingStyleCard(displayStyle: DisplayStyle, onViewSampleClicked: DisplayStyleHandler) =
    TextyDisplayStyleCard(
        title = displayStyle.toString(),
        description = "A dynamic style that makes text blink with versatile options: blink once, infinitely, for a set time, or a specified count. The blink delay is customizable for added flexibility.",
        variations = listOf(
            StyleVariation(
                name = "Default Blinking",
                code = """
                    Texty(
                        text = "Blinking Text",
                        displayStyle = DisplayStyle.Blinking()
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Blinking Text",
                        displayStyle = DisplayStyle.Blinking(),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            ),
            StyleVariation(
                name = "Blink Once",
                code = """
                    Texty(
                        text = "Blink Once",
                        displayStyle = DisplayStyle.Blinking(
                            blinkInterval = 1000L,
                            repeat = Repeat.Once
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Blink Once",
                        displayStyle = DisplayStyle.Blinking(
                            blinkInterval = 1000L,
                            repeat = Repeat.Once
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            ),
            StyleVariation(
                name = "Blink Ten Times",
                code = """
                    var count by remember { mutableStateOf(0) }
                    Texty(
                        text = "Blink count ${'$'}count",
                        displayStyle = DisplayStyle.Blinking(
                            blinkInterval = 500L,
                            repeat = Repeat.CountBound(
                                repeatCount = 10,
                                showAfterComplete = true
                            ),
                            onBlink = { count++ }
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    var count by remember { mutableStateOf(0) }
                    Texty(
                        text = "Blink count $count",
                        displayStyle = DisplayStyle.Blinking(
                            blinkInterval = 500L,
                            repeat = Repeat.CountBound(
                                repeatCount = 10,
                                showAfterComplete = true
                            ),
                            onBlink = { count++ }
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            ),
            StyleVariation(
                name = "Blinking for 10 seconds",
                code = """
                    Texty(
                        text = "Blinking for 10 seconds",
                        displayStyle = DisplayStyle.Blinking(
                            blinkInterval = 500L,
                            repeat = Repeat.TimeBound(
                                durationInMillis = 10_000L,
                                showAfterComplete = true
                            )
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = "Blinking for 10 seconds",
                        displayStyle = DisplayStyle.Blinking(
                            blinkInterval = 500L,
                            repeat = Repeat.TimeBound(
                                durationInMillis = 10_000L,
                                showAfterComplete = true
                            )
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            )
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )