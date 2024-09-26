package com.arjunjadeja.texty.styles

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.Utility
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.base.UtilityHandler
import com.arjunjadeja.texty.design_system.components.StyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get
import kotlin.time.Duration.Companion.seconds

@Composable
fun TimeKeepingStyle(
    utility: Utility,
    onViewSampleClicked: UtilityHandler
) = StyleCard(
    title = utility.toString(),
    description = "A utility style that displays time in a customizable format, with options for live updates and an adjustable update interval. Perfect for real-time time tracking.",
    variations = listOf(
        StyleVariation(
            name = "Default Time Keeping",
            code = """
                Texty(utility = Utility.TimeKeeping())
                """.trimIndent(),
            demoContent = {
                Texty(
                    utility = Utility.TimeKeeping(),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "Full Date Time Display",
            code = """
                Texty(
                    utility = Utility.TimeKeeping(
                        format = "yyyy-MM-dd HH:mm:ss",
                        liveUpdate = true
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    utility = Utility.TimeKeeping(
                        format = "yyyy-MM-dd HH:mm:ss",
                        liveUpdate = true
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        ),
        StyleVariation(
            name = "High Precision Time Keeping",
            code = """
                Texty(
                    utility = Utility.TimeKeeping(
                        format = "HH:mm:ss.SSS",
                        liveUpdate = true,
                        updateInterval = 0.1.seconds
                    )
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    utility = Utility.TimeKeeping(
                        format = "HH:mm:ss.SSS",
                        liveUpdate = true,
                        updateInterval = 0.1.seconds
                    ),
                    textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                )
            }
        )
    ),
    onViewSampleClicked = { onViewSampleClicked(utility) }
)