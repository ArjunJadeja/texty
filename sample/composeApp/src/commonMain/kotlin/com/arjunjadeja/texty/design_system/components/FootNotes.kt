package com.arjunjadeja.texty.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.Links
import com.arjunjadeja.texty.design_system.properties.AppDimens
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get
import com.arjunjadeja.texty.design_system.properties.white
import com.arjunjadeja.texty.design_system.theme.isDesktop
import org.jetbrains.compose.resources.vectorResource
import texty.sample.composeapp.generated.resources.Res
import texty.sample.composeapp.generated.resources.link

@Composable
fun FootNotes() = TextyCard(elevationType = CardElevationType.MEDIUM) {
    Box(contentAlignment = Alignment.Center) {
        if (isDesktop()) Texty(
            text = "made with love ❤️ by arjun jadeja",
            textStyle = TextyStyle.FOOTER.get(),
            modifier = Modifier.align(Alignment.Center)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppDimens.paddingMedium, horizontal = AppDimens.paddingBig)
                .background(color = white.copy(alpha = 0f))
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Texty(text = "all rights reserved", textStyle = TextyStyle.FOOTER.get())
            LinkToVisit()
        }
    }
}

@Composable
private fun LinkToVisit() = TextyIconButton(
    icon = vectorResource(resource = Res.drawable.link),
    contentDescription = "Personal Website Link",
    clickType = ClickType.Redirection(uri = Links.PERSONAL_WEBSITE)
)
