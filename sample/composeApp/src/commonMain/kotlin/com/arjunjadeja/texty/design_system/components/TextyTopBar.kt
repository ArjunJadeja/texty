package com.arjunjadeja.texty.design_system.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ClickHandler
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingMedium
import com.arjunjadeja.texty.design_system.properties.AppDimens.smallSpacer
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get
import org.jetbrains.compose.resources.vectorResource
import texty.sample.composeapp.generated.resources.Res
import texty.sample.composeapp.generated.resources.back

@Composable
fun TextyTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onTitleClick: ClickHandler = {},
    navigationType: NavigationType = NavigationType.Null,
    actions: @Composable RowScope.() -> Unit = {}
) = TextyCard(
    modifier = modifier,
    elevationType = CardElevationType.BIG
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = paddingMedium, horizontal = paddingBig)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            when (navigationType) {
                is NavigationType.Back -> {
                    BackButton { navigationType.onBackClick() }
                    Spacer(Modifier.width(smallSpacer))
                }

                NavigationType.Null -> {
                    /* no navigation option */
                }
            }
            Title(title = title, onTitleClick = onTitleClick)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterEnd),
            content = actions
        )
    }
}

@Composable
private fun BackButton(onClick: ClickHandler) = TextyIconButton(
    icon = vectorResource(resource = Res.drawable.back),
    contentDescription = "Back Button",
    clickType = ClickType.CallBack,
    onClick = onClick
)

@Composable
private fun Title(title: String, onTitleClick: ClickHandler) = Texty(
    text = title,
    modifier = Modifier.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onTitleClick
    ),
    textStyle = TextyStyle.TOP_BAR_TITLE.get()
)

sealed interface NavigationType {
    data object Null : NavigationType
    data class Back(val onBackClick: ClickHandler) : NavigationType
}
