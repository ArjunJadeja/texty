package com.arjunjadeja.texty.styles

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.TransitionStyle
import com.arjunjadeja.texty.base.ListDisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.StyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun OneByOneStyle(
    displayStyle: ListDisplayStyle,
    onViewSampleClicked: ListDisplayStyleHandler
) = StyleCard(
    title = displayStyle.toString(),
    description = "A fluid, sequential text display where each word or character gracefully appears with customizable effects—Basic, Fading, or Typing. With tailored durations and repeat options, it brings rhythm and motion to storytelling, presentations, or interactive displays.",
    variations = listOf(
        StyleVariation(
            name = "Default One By One",
            code = """
                Texty(
                    content = listOf("One", "Two", "three"),
                    displayStyle = ListDisplayStyle.OneByOne()
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    textList = listOf("One", "Two", "three"),
                    displayStyle = ListDisplayStyle.OneByOne(),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Typing and Clearing",
            code = """
                Texty(
                    textList = listOf("Typing", "and", "Clearing"),
                    displayStyle = ListDisplayStyle.OneByOne(
                        transitionStyle = TransitionStyle.TYPING,
                        transitionInDuration = 300L,
                        transitionOutDuration = 400L,
                        displayDuration = 500L
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    textList = listOf("Typing", "and", "Clearing"),
                    displayStyle = ListDisplayStyle.OneByOne(
                        transitionStyle = TransitionStyle.TYPING,
                        transitionInDuration = 300L,
                        transitionOutDuration = 400L,
                        displayDuration = 500L
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Fast Sequential Display",
            code = """
                Texty(
                    textList = listOf("Quick update 1", "Quick update 2", "Quick update 3"),
                    displayStyle = ListDisplayStyle.OneByOne(
                        displayDuration = 50L,
                        transitionInDuration = 200L,
                        transitionOutDuration = 200L
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    textList = listOf("Quick update 1", "Quick update 2", "Quick update 3"),
                    displayStyle = ListDisplayStyle.OneByOne(
                        displayDuration = 50L,
                        transitionInDuration = 200L,
                        transitionOutDuration = 200L
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Fading In and Out 3 times",
            code = """
                Texty(
                    textList = listOf("breadth in...", "breadth out..."),
                    displayStyle = ListDisplayStyle.OneByOne(
                        transitionStyle = TransitionStyle.FADING,
                        transitionInDuration = 800L,
                        transitionOutDuration = 800L,
                        displayDuration = 800L,
                        repeat = Repeat.CountBound(count = 3, showAfterComplete = true),
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    textList = listOf("breadth in...", "breadth out..."),
                    displayStyle = ListDisplayStyle.OneByOne(
                        transitionStyle = TransitionStyle.FADING,
                        transitionInDuration = 800L,
                        transitionOutDuration = 800L,
                        displayDuration = 800L,
                        repeat = Repeat.CountBound(count = 3, showAfterComplete = true),
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),

        ),
    onViewSampleClicked = { onViewSampleClicked(displayStyle) }
)