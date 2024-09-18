package com.arjunjadeja.texty.design_system.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import com.arjunjadeja.texty.base.ClickHandler

@Composable
fun TextyIconButton(
    icon: ImageVector,
    contentDescription: String,
    clickType: ClickType,
    onClick: ClickHandler = {}
) {
    val uriHandler = LocalUriHandler.current

    IconButton(
        onClick = {
            when (clickType) {
                ClickType.CallBack -> onClick()
                is ClickType.Redirection -> uriHandler.openUri(clickType.uri)
            }
        }
    ) { Icon(imageVector = icon, contentDescription = contentDescription) }
}

sealed interface ClickType {
    data object CallBack : ClickType
    data class Redirection(val uri: String) : ClickType
}
