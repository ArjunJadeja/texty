package com.arjunjadeja.texty.design_system.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardCornerRadius
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardElevationBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardElevationMedium
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardElevationSmall
import com.arjunjadeja.texty.design_system.properties.AppDimens.minCardHeight
import com.arjunjadeja.texty.design_system.properties.AppDimens.cardElevationNil
import com.arjunjadeja.texty.design_system.properties.AppDimens.borderStrokeWidth

@Composable
fun TextyCard(
    elevationType: CardElevationType,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = cardCornerRadius,
    showBorder: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) = Card(
    modifier = modifier
        .heightIn(min = minCardHeight)
        .fillMaxWidth()
        .then(
            if (showBorder) {
                Modifier
                    .clip(shape = RoundedCornerShape(cornerRadius))
                    .border(
                        width = borderStrokeWidth,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(cornerRadius)
                    )
            } else Modifier
        ),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = when (elevationType) {
        CardElevationType.NIL -> CardDefaults.cardElevation(defaultElevation = cardElevationNil)
        CardElevationType.SMALL -> CardDefaults.cardElevation(defaultElevation = cardElevationSmall)
        CardElevationType.MEDIUM -> CardDefaults.cardElevation(defaultElevation = cardElevationMedium)
        CardElevationType.BIG -> CardDefaults.cardElevation(defaultElevation = cardElevationBig)
    },
    shape = RoundedCornerShape(size = cornerRadius),
    content = content
)

enum class CardElevationType {
    NIL,
    SMALL,
    MEDIUM,
    BIG
}
