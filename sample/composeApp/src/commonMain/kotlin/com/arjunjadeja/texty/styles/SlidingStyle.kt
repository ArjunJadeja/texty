package com.arjunjadeja.texty.styles

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.StyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun SlidingStyle(
    displayStyle: DisplayStyle,
    onViewSampleClicked: DisplayStyleHandler
) = StyleCard(
    title = displayStyle.toString(),
    description = "A captivating style where text glides smoothly in a chosen direction. With customizable duration and repeat options, it creates a rhythmic flow that enhances visual storytelling.",
    variations = listOf(
        StyleVariation(
            name = "Default Sliding",
            code = """
                Texty(
                    text = "Sliding Text",
                    displayStyle = DisplayStyle.Sliding()
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    text = "Sliding Text",
                    displayStyle = DisplayStyle.Sliding(),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ), StyleVariation(
            name = "Sliding Towards End",
            code = """
                Texty(
                    text = "Sliding Text",
                    displayStyle = DisplayStyle.Sliding(
                        slidingDirection = SlidingDirection.TowardsEnd
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    text = "Sliding Text",
                    displayStyle = DisplayStyle.Sliding(
                        slidingDirection = SlidingDirection.TowardsEnd
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        )
    ),
    onViewSampleClicked = { onViewSampleClicked(displayStyle) }
)