package com.arjunjadeja.texty.styles

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ListDisplayStyleHandler
import com.arjunjadeja.texty.base.StyleVariation
import com.arjunjadeja.texty.design_system.components.StyleCard
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun MotionStyle(
    displayStyle: ListDisplayStyle,
    onViewSampleClicked: ListDisplayStyleHandler
) = StyleCard(
    title = displayStyle.toString(),
    description = "A dynamic style that animates text or frames sequentially, creating a smooth flow. With customizable display delay and flexible repeat options, it delivers continuous motion, perfect for animations or creating a cinematic feel.",
    variations = listOf(
        StyleVariation(
            name = "Default Motion",
            code = """
                Texty(
                    textList = listOf(
                        frame_one, frame_two, frame_three, frame_four, frame_five,
                        frame_six, frame_seven, frame_eight
                    ),
                    displayStyle = ListDisplayStyle.Motion()
                )
                """.trimIndent(),
            demoContent = {
                Texty(
                    textList = listOf(
                        frame_one, frame_two, frame_three, frame_four, frame_five,
                        frame_six, frame_seven, frame_eight
                    ),
                    displayStyle = ListDisplayStyle.Motion(),
                    textStyle = TextyStyle.CODE_LABEL.get()
                )
            }
        ),
        StyleVariation(
            name = "Little Slow Motion for 10 seconds",
            code = """
                Texty(
                    textList = listOf(
                        frame_one, frame_two, frame_three, frame_four, frame_five,
                        frame_six, frame_seven, frame_eight
                    ),
                    displayStyle = ListDisplayStyle.Motion(
                        delayBeforeNext = 500L,
                        repeat = Repeat.TimeBound(
                            duration = 10_000L,
                            showAfterComplete = true
                        )
                    )
                )
                    """.trimIndent(),
            demoContent = {
                Texty(
                    textList = listOf(
                        frame_one, frame_two, frame_three, frame_four, frame_five,
                        frame_six, frame_seven, frame_eight
                    ),
                    displayStyle = ListDisplayStyle.Motion(
                        delayBeforeNext = 500L,
                        repeat = Repeat.TimeBound(
                            duration = 10_000L,
                            showAfterComplete = true
                        )
                    ),
                    textStyle = TextyStyle.CODE_LABEL.get()
                )
            }
        )
    ),
    onViewSampleClicked = { onViewSampleClicked(displayStyle) }
)

private val frame_one by lazy {
    """
    ⢤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡄⠀⠀⠀⠀⠀
    ⠘⣿⠏⠒⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠒⠙⣿⡟⠀⠀⠀⠀⠀⠀
    ⠀⠘⢆⠀⠀⠑⢄⠠⠄⠀⠒⠂⠀⠤⡐⠁⠀⠀⢠⠋⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠢⡀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢄⠔⠁⠀⠀⠀⠀⠀⢀⡠⡄
    ⠀⠀⠀⠀⡈⠀⢀⡀⠀⠀⠀⠀⠀⢀⣀⠀⠸⠀⠀⠀⠀⠀⡠⠂⠁⠀⠇
    ⠀⠀⠀⢀⠇⠀⠿⠶⠀⠀⢀⡀⠀⠷⠿⠀⠠⡀⠀⢀⠔⠁⠀⠀⠀⢠⠀
    ⠀⠀⠀⢸⢘⠉⡂⠀⠀⣀⡈⣀⡀⠀⢰⠂⢢⡇⠰⡁⠀⠀⠀⢀⠠⠊⠀
    ⠀⠀⢀⠔⡩⠛⠒⠒⢤⠀⠀⠀⠀⠀⠈⠒⠑⢣⠀⠡⠀⠀⡎⠀⠀⠀⠀
    ⠀⠀⡎⡎⡄⠀⠀⠁⣨⡇⠀⠠⠀⢀⠔⠒⠲⢄⡃⢀⣀⣀⣠⠀⠀⠀⠀
    ⠀⠀⠑⢧⣛⣥⣤⣾⣿⠁⠀⠀⠀⡿⡇⠀⠐⠄⠱⡌⣆⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⢨⠉⠉⠀⠉⠁⠀⠀⠀⠀⣿⣿⣶⣿⣶⣶⠏⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⢰⣁⠽⠂⠒⠒⠀⠐⠒⠠⠄⡀⠠⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠴⠵⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    """.trimIndent()
}

private val frame_two by lazy {
    """
    ⢤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⡄⠀⠀⠀⠀⠀
    ⠘⣿⠏⠒⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠒⠙⣿⡟⠀⠀⠀⠀⠀⠀
    ⠀⠘⢆⠀⠀⠑⢄⠠⠄⠀⠒⠂⠀⠤⡐⠁⠀⠀⢠⠋⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠢⡀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢄⠔⠁⠀⠀⠀⠀⠀⢀⡠⡄
    ⠀⠀⠀⠀⡈⠀⢀⡀⠀⠀⠀⠀⠀⢀⣀⠀⠸⠀⠀⠀⠀⠀⡠⠂⠁⠀⠇
    ⠀⠀⠀⢀⠇⠀⠿⠶⠀⠀⢀⡀⠀⠷⠿⠀⠠⡀⠀⢀⠔⠁⠀⠀⠀⢠⠀
    ⠀⠀⠀⢸⢘⠉⡂⠀⠀⣀⡈⣀⡀⠀⢰⠂⢢⡇⠰⡁⠀⠀⠀⢀⠠⠊⠀
    ⠀⠀⢀⠔⡩⠛⠒⠒⢤⠀⠀⠀⠀⠀⠈⠒⠑⢣⠀⠡⠀⠀⡎⠀⠀⠀⠀
    ⠀⠀⡎⡎⡄⠀⠀⠁⣨⡇⠀⠠⠀⢀⠔⠒⠲⢄⡃⢀⣀⣀⣠⠀⠀⠀⠀
    ⠀⠀⠑⢧⣛⣥⣤⣾⣿⠁⠀⠀⠀⡿⡇⠀⠐⠄⠱⡌⣆⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⢨⠉⠉⠀⠉⠁⠀⠀⠀⠀⣿⣿⣶⣿⣶⣶⠏⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⢰⣁⠽⠂⠒⠒⠀⠐⠒⠠⠄⡀⠠⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠴⠵⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    """.trimIndent()
}

private val frame_three by lazy {
    """
    ⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⠇⠀⠀
    ⠀⢻⣿⠒⠤⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⠊⠸⡟⠀⠀⠀
    ⠀⠀⠻⡀⠀⠀⠑⠢⠠⠄⠀⠀⠀⠠⠄⡰⠃⠀⢀⡜⠀⠀⠀⣀
    ⠀⠀⠀⠈⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣠⠊⠀⢀⠄⠈⢸
    ⠀⠀⠀⠀⠀⡜⠁⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⢸⠠⠊⠁⠀⠀⢸
    ⠀⠀⠀⠀⢀⠃⠀⠀⢿⣾⠃⠀⠀⠀⠐⣽⡇⢸⠀⠀⠀⠀⢀⡸
    ⠀⠀⠀⠀⣨⣀⠮⠷⢤⡀⠀⢀⠀⠁⠀⠀⡠⢲⠆⠀⠐⠈⠁⠀
    ⠀⠀⢀⠞⡱⠒⠀⡤⠄⠘⡆⠀⠁⠉⠀⠀⣑⣚⡀⠀⠀⠀⠀⠀
    ⠀⠀⢸⠾⡍⡀⠀⠀⣋⣤⡟⠄⠀⠄⢀⡎⡅⠀⠈⠳⡄⠀⠀⠀
    ⠀⠀⠈⢷⣶⣶⣶⣿⣿⣿⠁⠀⠀⠀⣼⣽⣦⡀⠀⠀⢹⠀⠀⠀
    ⠀⠀⠀⢀⠃⠀⠈⠉⠉⠀⠀⠀⠀⠀⠈⠛⠿⣿⣿⣶⠟⠀⠀⠀
    ⠀⠀⠀⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡅⠀⠀⠀⠀⠀
    ⠀⠀⠀⠐⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⢐⣁⠵⠒⠒⠒⠒⠒⠒⠠⠤⡀⠀⡔⠁⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠄⠵⠀⠀⠀⠀⠀⠀⠀
    """.trimIndent()
}

private val frame_four by lazy {
    """
    ⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⠇⠀⠀
    ⠀⢻⣿⠒⠤⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠤⠊⠸⡟⠀⠀⠀
    ⠀⠀⠻⡀⠀⠀⠑⠢⠠⠄⠀⠀⠀⠠⠄⡰⠃⠀⢀⡜⠀⠀⠀⣀
    ⠀⠀⠀⠈⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣠⠊⠀⢀⠄⠈⢸
    ⠀⠀⠀⠀⠀⡜⠁⠀⢀⡀⠀⠀⠀⠀⠀⠀⠀⢸⠠⠊⠁⠀⠀⢸
    ⠀⠀⠀⠀⢀⠃⠀⠀⢿⣾⠃⠀⠀⠀⠐⣽⡇⢸⠀⠀⠀⠀⢀⡸
    ⠀⠀⠀⠀⣨⣀⠮⠷⢤⡀⠀⢀⠀⠁⠀⠀⡠⢲⠆⠀⠐⠈⠁⠀
    ⠀⠀⢀⠞⡱⠒⠀⡤⠄⠘⡆⠀⠁⠉⠀⠀⣑⣚⡀⠀⠀⠀⠀⠀
    ⠀⠀⢸⠾⡍⡀⠀⠀⣋⣤⡟⠄⠀⠄⢀⡎⡅⠀⠈⠳⡄⠀⠀⠀
    ⠀⠀⠈⢷⣶⣶⣶⣿⣿⣿⠁⠀⠀⠀⣼⣽⣦⡀⠀⠀⢹⠀⠀⠀
    ⠀⠀⠀⢀⠃⠀⠈⠉⠉⠀⠀⠀⠀⠀⠈⠛⠿⣿⣿⣶⠟⠀⠀⠀
    ⠀⠀⠀⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡅⠀⠀⠀⠀⠀
    ⠀⠀⠀⠐⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⢐⣁⠵⠒⠒⠒⠒⠒⠒⠠⠤⡀⠀⡔⠁⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠄⠵⠀⠀⠀⠀⠀⠀⠀
    """.trimIndent()
}

private val frame_five by lazy {
    """
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣶⡇⠀
    ⢰⣶⣦⡤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⠉⢿⠃⠀
    ⠀⠙⢿⡇⠀⠈⠑⠢⣀⠀⠀⣀⣀⣀⣀⡀⠀⢠⠊⠀⠀⣜⣠⠀
    ⠀⠀⠀⠑⢄⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠈⠑⠃⡀⠀⡜⠁⢸⠀
    ⠀⠀⠀⠀⠀⠐⢢⢤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡎⠀⠀⠀⡄
    ⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⢄⠀⠀⠀⠀⠀⠀⡀⢰⠀⠀⢀⡇
    ⠀⠀⠀⠀⠀⢰⠀⠀⢀⠀⢷⣾⡇⠀⠀⠀⠀⣯⡷⢸⠒⠈⠁⠀
    ⠀⠀⢀⡴⠚⠛⠩⠩⠍⠑⢦⡀⠀⠀⡀⠀⢒⠀⢀⠖⡇⠀⠀⠀
    ⠀⠀⡾⡾⠃⠀⠈⢤⠤⣄⠀⣷⠀⠀⠙⠭⠋⠀⠘⣺⠥⢤⡀⠀
    ⠀⢸⡇⣇⣂⠀⠄⠤⠄⢏⣀⣹⠀⢀⢀⡀⠀⣠⢎⡀⠀⠀⠙⣦
    ⠀⠀⠻⣿⣿⣿⣷⣶⣿⣿⣿⠟⠀⠀⠀⠀⠀⣿⣥⣄⠀⠀⠀⢹
    ⠀⠀⠀⢈⠋⠙⢿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠙⢻⣿⣷⣤⣘⡾
    ⠀⠀⠀⡘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡟⠛⠛⠋⠀
    ⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀
    ⠀⠀⠀⢘⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠃⠀⠀⠀⠀
    ⠀⠀⠀⢧⡤⠊⠉⠉⠉⠉⠉⠉⠐⠒⠤⠄⠠⡊⠁⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠤⠇⠀⠀⠀⠀⠀⠀
    """.trimIndent()
}

private val frame_six by lazy {
    """
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣶⡇⠀
    ⢰⣶⣦⡤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠔⠉⢿⠃⠀
    ⠀⠙⢿⡇⠀⠈⠑⠢⣀⠀⠀⣀⣀⣀⣀⡀⠀⢠⠊⠀⠀⣜⣠⠀
    ⠀⠀⠀⠑⢄⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠈⠑⠃⡀⠀⡜⠁⢸⠀
    ⠀⠀⠀⠀⠀⠐⢢⢤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡎⠀⠀⠀⡄
    ⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⢄⠀⠀⠀⠀⠀⠀⡀⢰⠀⠀⢀⡇
    ⠀⠀⠀⠀⠀⢰⠀⠀⢀⠀⢷⣾⡇⠀⠀⠀⠀⣯⡗⢸⠒⠈⠁⠀
    ⠀⠀⢀⡴⠚⠛⠩⠩⠍⠑⢦⡀⠀⠀⠀⠀⠒⠀⢀⠖⡇⠀⠀⠀
    ⠀⠀⡾⡾⠃⠀⠈⢤⠤⣄⠀⣵⠀⠀⠙⠭⠋⠀⠘⣺⠥⢤⡀⠀
    ⠀⢸⡇⣇⣂⠀⠄⠤⠄⢏⣀⣹⠀⢀⢀⡀⠀⣠⢎⡀⠀⠀⠙⣦
    ⠀⠀⠻⣿⣿⣿⣷⣶⣿⣿⣿⠟⠀⠀⠀⠀⠀⣿⣥⣄⠀⠀⠀⢹
    ⠀⠀⠀⢈⠋⠙⢿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠙⢻⣿⣷⣤⣘⡾
    ⠀⠀⠀⡘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡟⠛⠛⠋⠀
    ⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀
    ⠀⠀⠀⢘⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠃⠀⠀⠀⠀
    ⠀⠀⠀⢧⡤⠊⠉⠉⠉⠉⠉⠉⠐⠒⠤⠄⠠⡊⠁⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠤⠇⠀⠀⠀⠀⠀⠀
    """.trimIndent()
}

private val frame_seven by lazy {
    """
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣞⠃⠀⠀
    ⢻⣿⡖⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠔⠁⣿⠇⠀⠀⠀
    ⠀⠻⡇⠀⠀⠉⠢⢄⣀⠠⠤⠤⠤⠄⣀⣠⠚⠀⠀⢠⠋⠀⠀⠀⠀
    ⠀⠀⠈⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⡰⠁⠀⢀⠤⠊⡇
    ⠀⠀⠀⠀⠈⠖⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡄⡠⠒⠁⠀⠀⡇
    ⠀⠀⠀⠀⢸⠀⠀⢠⣮⣥⠄⠀⠀⠀⠀⣤⣦⠀⠃⠀⠀⠀⠀⠀⡇
    ⠀⠀⠀⠀⠇⢀⢤⣌⠙⠋⠀⠀⠀⡤⠀⠙⠃⣀⣃⠀⢀⡠⠔⠒⠁
    ⠀⠀⢀⠴⢓⡉⠉⠈⠙⢦⡀⠐⠔⠦⠂⠀⢺⣀⡏⠀⡇⠀⠀⠀⠀
    ⠀⠀⣯⠈⠂⠀⠊⠀⠄⠈⣇⠀⠀⠀⠀⣠⠴⠚⠲⢤⡇⠀⠀⠀⠀
    ⠀⠀⣟⣐⣒⣀⣂⣤⣶⣿⠇⠁⠒⠁⢀⣷⠇⠀⠀⠀⢹⡄⠀⠀⠀
    ⠀⠀⠈⠻⠿⠿⣿⣿⣿⠟⠀⠀⠀⠀⢸⣿⣿⣦⣀⣀⣀⡇⠀⠀⠀
    ⠀⠀⠀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣿⠿⠟⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⢢⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠃⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⣅⡠⠋⠁⠀⠈⠀⠐⠒⠂⠤⡤⠤⡔⠃⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠦⠼⠀⠀⠀⠀⠀⠀⠀⠀
    """.trimIndent()
}

private val frame_eight by lazy {
    """
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣞⠃⠀⠀
    ⢻⣿⡖⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠔⠁⣿⠇⠀⠀⠀
    ⠀⠻⡇⠀⠀⠉⠢⢄⣀⠠⠤⠤⠤⠄⣀⣠⠚⠀⠀⢠⠋⠀⠀⠀⠀
    ⠀⠀⠈⠢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⡰⠁⠀⢀⠤⠊⡇
    ⠀⠀⠀⠀⠈⠖⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡄⡠⠒⠁⠀⠀⡇
    ⠀⠀⠀⠀⢸⠀⠀⢠⣮⣥⠄⠀⠀⠀⠀⣤⣦⠀⠃⠀⠀⠀⠀⠀⡇
    ⠀⠀⠀⠀⠇⢀⢤⣌⠙⠋⠀⠀⠀⡤⠀⠙⠃⣀⣃⠀⢀⡠⠔⠒⠁
    ⠀⠀⢀⠴⢓⡉⠉⠈⠙⢦⡀⠐⠔⠦⠂⠀⢺⣀⡏⠀⡇⠀⠀⠀⠀
    ⠀⠀⣯⠈⠂⠀⠊⠀⠄⠈⣇⠀⠀⠀⠀⣠⠴⠚⠲⢤⡇⠀⠀⠀⠀
    ⠀⠀⣟⣐⣒⣀⣂⣤⣶⣿⠇⠁⠒⠁⢀⣷⠇⠀⠀⠀⢹⡄⠀⠀⠀
    ⠀⠀⠈⠻⠿⠿⣿⣿⣿⠟⠀⠀⠀⠀⢸⣿⣿⣦⣀⣀⣀⡇⠀⠀⠀
    ⠀⠀⠀⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣿⠿⠟⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⢢⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠃⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⣅⡠⠋⠁⠀⠈⠀⠐⠒⠂⠤⡤⠤⡔⠃⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠦⠼⠀⠀⠀⠀⠀⠀⠀⠀
    """.trimIndent()
}