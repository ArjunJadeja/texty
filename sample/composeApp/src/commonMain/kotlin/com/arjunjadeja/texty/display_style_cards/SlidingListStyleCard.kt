package com.arjunjadeja.texty.display_style_cards

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ListDisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun SlidingListStyleCard(
    displayStyle: ListDisplayStyle,
    onViewSampleClicked: ListDisplayStyleHandler
) {
    TextyDisplayStyleCard(
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
                            slidingDirection = SlidingDirection.TowardsStart,
                            slideDuration = 4000L
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        textList = listOf("Inspire", "Innovate", "Impact"),
                        displayStyle = ListDisplayStyle.SlidingList(
                            separator = " <-> ",
                            slidingDirection = SlidingDirection.TowardsEnd,
                            slideDuration = 4000L
                        ),
                        textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                    )
                }
            )
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )
}
