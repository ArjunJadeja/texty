package com.arjunjadeja.texty.display_style_cards

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.SlidingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun StickAndRevealStyleCard(displayStyle: DisplayStyle, onViewSampleClicked: DisplayStyleHandler) =
    TextyDisplayStyleCard(
        title = displayStyle.toString(),
        description = "A captivating style where a cover sticks to the surface before being revealed, simulating a realistic poster or banner effect. With adjustable delays and directions, it adds drama and depth to your animations.",
        variations = listOf(
            StyleVariation(
                name = "Default Stick and Reveal",
                code = """
                    Texty(
                        text = frame,
                        displayStyle = DisplayStyle.StickAndReveal()
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = frame,
                        displayStyle = DisplayStyle.StickAndReveal(),
                        textStyle = TextyStyle.CODE_LABEL.get()
                    )
                }
            ),
            StyleVariation(
                name = "Quick Sliding",
                code = """
                    Texty(
                        text = frame,
                        displayStyle = DisplayStyle.StickAndReveal(
                            stickingDelay = 30L,
                            revealingDelay = 30L,
                            delayBeforeReveal = 100L,
                            stickingDirection = SlidingDirection.LEFT_TO_RIGHT,
                            revealingDirection = SlidingDirection.RIGHT_TO_LEFT
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Texty(
                        text = frame,
                        displayStyle = DisplayStyle.StickAndReveal(
                            stickingDelay = 30L,
                            revealingDelay = 30L,
                            delayBeforeReveal = 100L,
                            stickingDirection = SlidingDirection.LEFT_TO_RIGHT,
                            revealingDirection = SlidingDirection.RIGHT_TO_LEFT
                        ),
                        textStyle = TextyStyle.CODE_LABEL.get()
                    )
                }
            )
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )

private val frame by lazy {
    """
⠀⠀⠀⠀⠀⠀⡠⠂⠀⠒⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⢀⠀⠀⠀⠀⠀⠀
⠀⠠⠀⢀⠀⠀⠆⠀⠀⠀⠀⠑⠀⡀⠀⠀⠀⠀⢀⠄⠊⠀⠀⠀⠐⡀⠀⠀⠀⠀
⢠⠁⠀⡜⠀⢠⠀⠀⠀⠀⠀⠀⠀⠈⠢⠀⠄⠈⠀⠀⠀⠀⠀⠀⠀⠅⠀⠀⠀⠀
⠀⠀⢰⠀⠀⢸⠀⣶⣾⣶⣶⣶⣶⣶⣶⣶⣤⣤⣤⣤⣤⣤⣄⡀⠈⠀⠀⠀⠀⠀
⠰⡀⠀⢂⠀⠘⢰⣿⣿⠋⣡⣤⣤⡙⢿⣿⠟⢉⣉⡉⠻⣿⣿⡇⢸⠀⠀⠀⠀⠀
⠀⠑⠀⠀⠑⠄⢸⣿⣿⠐⣿⣿⣿⡇⢸⡯⢰⣿⣿⣿⡆⢹⣿⣷⢸⠀⠀⠀⠀⠀
⠀⠀⠀⠐⠄⡀⢸⣿⣿⣦⣌⣉⣉⣴⣿⣿⣄⠙⠛⠋⣠⣿⣿⡏⠀⠒⠠⢀⠀⠀
⠀⠀⠀⠀⠀⢇⠘⠿⠿⠿⣿⣿⣿⣿⣶⣶⣿⣿⣿⣿⣿⣿⣿⠃⡆⠤⡀⠀⠑⡀
⠀⠀⠀⠀⠀⠀⠁⠒⠒⠂⠂⠀⠀⠀⣤⠀⠀⠍⠉⠉⠉⢉⣁⠄⠁⠀⠈⡆⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠆⠀⠁⠀⠰⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠁⠀⡄
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠄⠀⠀⠆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠣⠠⠈⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠀⢀⠀⠀⠉⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠤⠃⠀⡜⠈⠁⢲⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠆⣀⡀⢀⠃⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀        
""".trimIndent()
}
