package com.arjunjadeja.texty.display_style_cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ListDisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.TextyDisplayStyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun ScrollingListStyleCard(
    displayStyle: ListDisplayStyle,
    onViewSampleClicked: ListDisplayStyleHandler
) {
    TextyDisplayStyleCard(
        title = displayStyle.toString(),
        description = "A vertical display where list items scroll effortlessly, either upwards or downwards, offering a dynamic and engaging presentation of information.",
        variations = listOf(
            StyleVariation(
                name = "Default Scrolling List",
                code = """
                    Box(modifier = Modifier.height(256.dp)) {
                        Texty(
                            textList = listOf("Imagine", "Design", "Build"),
                            displayStyle = ListDisplayStyle.ScrollingList()
                        )
                    }
                    """.trimIndent(),
                demoContent = {
                    Box(modifier = Modifier.height(256.dp)) {
                        Texty(
                            textList = listOf("Imagine", "Design", "Build"),
                            displayStyle = ListDisplayStyle.ScrollingList(),
                            textStyle = TextyStyle.DISPLAY_STYLE_DEMO_TEXT.get()
                        )
                    }
                }
            )
        ),
        onViewSampleClicked = { onViewSampleClicked(displayStyle) }
    )
}
