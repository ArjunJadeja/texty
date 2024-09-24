package com.arjunjadeja.texty.styles

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.LoadingType
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.Utility
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.base.UtilityHandler
import com.arjunjadeja.texty.design_system.components.StyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun LoadingStyle(
    utility: Utility,
    onViewSampleClicked: UtilityHandler
) = StyleCard(
    title = utility.toString(),
    description = "A versatile style offering various loading animations, including spinner, circular, box, and music bars. Each type features customizable cycle duration, with the music bar option allowing for adjustable bar count.",
    variations = listOf(
        StyleVariation(
            name = "Default Loading",
            code = """
                Texty(utility = Utility.Loading())
                """.trimIndent(),
            demoContent = {
                Texty(
                    utility = Utility.Loading(),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Circular Loading",
            code = """
                Texty(utility = Utility.Loading(type = LoadingType.Circular()))
                """.trimIndent(),
            demoContent = {
                Texty(
                    utility = Utility.Loading(type = LoadingType.Circular()),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Music Bar Loading",
            code = """
                Texty(utility = Utility.Loading(type = LoadingType.MusicBar()))
                """.trimIndent(),
            demoContent = {
                Texty(
                    utility = Utility.Loading(type = LoadingType.MusicBar()),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        )
    ),
    onViewSampleClicked = { onViewSampleClicked(utility) }
)