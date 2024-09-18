package com.arjunjadeja.texty.design_system.properties

object Gradients {
    val backgroundGradient = listOf(
        primaryGradientColor.copy(alpha = 0.2f),
        secondaryGradientColor.copy(alpha = 0.2f),
        tertiaryGradientColor.copy(alpha = 0.2f),
    )
    val textGradient = listOf(primaryGradientColor, secondaryGradientColor, tertiaryGradientColor)
}
