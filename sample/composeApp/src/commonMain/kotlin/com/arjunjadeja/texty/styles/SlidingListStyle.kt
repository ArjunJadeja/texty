package com.arjunjadeja.texty.styles

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ListDisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.StyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun SlidingListStyle(
    displayStyle: ListDisplayStyle,
    onViewSampleClicked: ListDisplayStyleHandler
) = StyleCard(
    title = displayStyle.toString(),
    description = "A fluid display where list items gracefully slide in a chosen direction, creating a smooth, continuous flow that adds motion and elegance to your content.",
    variations = listOf(
        StyleVariation(
            name = "Default Sliding List",
            code = """
                Texty(
                    textList = listOf("Explore", "Engage", "Evolve"),
                    displayStyle = ListDisplayStyle.SlidingList()
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    textList = listOf("Explore", "Engage", "Evolve"),
                    displayStyle = ListDisplayStyle.SlidingList(),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Sliding Towards End Slowly with <-> as separator",
            code = """
                Texty(
                    textList = listOf("Inspire", "Innovate", "Impact"),
                    displayStyle = ListDisplayStyle.SlidingList(
                        separator = " <-> ",
                        direction = SlidingDirection.TOWARDS_END,
                        duration = 4000L
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    textList = listOf("Inspire", "Innovate", "Impact"),
                    displayStyle = ListDisplayStyle.SlidingList(
                        separator = " <-> ",
                        direction = SlidingDirection.TOWARDS_END,
                        duration = 4000L
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        )
    ),
    onViewSampleClicked = { onViewSampleClicked(displayStyle) }
)