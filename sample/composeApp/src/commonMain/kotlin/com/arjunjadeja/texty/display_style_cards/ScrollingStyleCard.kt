package com.arjunjadeja.texty.display_style_cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.ScrollingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.DisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun ScrollingStyleCard(displayStyle: DisplayStyle, onViewSampleClicked: DisplayStyleHandler) {
    TextyDisplayStyleCard(
        title = displayStyle.toString(),
        description = "A dynamic style where text flows smoothly in a selected direction—top or bottom. With adjustable duration and repeat options, it captivates and engages viewers effortlessly.",
        variations = listOf(
            StyleVariation(
                name = "Default Scrolling",
                code = """
                    Texty(
                        text = "Scrolling Text",
                        displayStyle = DisplayStyle.Scrolling()
                    )
                    """.trimIndent(),
                demoContent = {
                    Box(modifier = Modifier.height(256.dp)) {
                        Texty(
                            text = "Scrolling Text",
                            displayStyle = DisplayStyle.Scrolling(),
                            textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                        )
                    }
                }
            ), StyleVariation(
                name = "Sliding Down",
                code = """
                    Texty(
                        text = "Scrolling Text",
                        displayStyle = DisplayStyle.Scrolling(
                            scrollingDirection = ScrollingDirection.TowardsBottom
                        )
                    )
                    """.trimIndent(),
                demoContent = {
                    Box(modifier = Modifier.height(256.dp)) {
                        Texty(
                            text = "Scrolling Text",
                            displayStyle = DisplayStyle.Scrolling(
                                scrollingDirection = ScrollingDirection.TowardsBottom
                            ),
                            textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                        )
                    }
                }
            )
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )
}