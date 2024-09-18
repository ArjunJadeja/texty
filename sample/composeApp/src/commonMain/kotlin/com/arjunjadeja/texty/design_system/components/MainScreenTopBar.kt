package com.arjunjadeja.texty.design_system.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arjunjadeja.texty.base.ClickHandler
import com.arjunjadeja.texty.base.Links
import com.arjunjadeja.texty.design_system.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import texty.sample.composeapp.generated.resources.Res
import texty.sample.composeapp.generated.resources.dark_mode
import texty.sample.composeapp.generated.resources.github
import texty.sample.composeapp.generated.resources.light_mode
import texty.sample.composeapp.generated.resources.texty

@Composable
fun MainScreenTopBar(onTitleClick: ClickHandler) = TextyTopBar(
    title = stringResource(Res.string.texty),
    onTitleClick = onTitleClick,
    navigationType = NavigationType.Null
) {
    GithubRepository()
    ThemeToggle()
}

@Composable
private fun GithubRepository() = TextyIconButton(
    icon = vectorResource(resource = Res.drawable.github),
    contentDescription = "Texty Github Repository",
    clickType = ClickType.Redirection(uri = Links.TEXTY_GITHUB_REPOSITORY)
)

@Composable
private fun ThemeToggle() {
    var isDark by LocalThemeIsDark.current
    val icon = remember(isDark) { if (isDark) Res.drawable.light_mode else Res.drawable.dark_mode }
    val contentDescription = if (isDark) "Switch to light theme" else "Switch to dark theme"

    TextyIconButton(
        icon = vectorResource(resource = icon),
        contentDescription = contentDescription,
        clickType = ClickType.CallBack
    ) { isDark = !isDark }
}
