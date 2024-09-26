package com.arjunjadeja.texty.design_system.components

import androidx.compose.runtime.Composable
import com.arjunjadeja.texty.base.ClickHandler
import com.arjunjadeja.texty.design_system.components.core.NavigationType
import com.arjunjadeja.texty.design_system.components.core.TextyTopBar

@Composable
fun SampleScreenTopBar(
    title: String,
    onBackClick: ClickHandler,
    onTitleClick: ClickHandler = {}
) = TextyTopBar(
    title = title,
    onTitleClick = onTitleClick,
    navigationType = NavigationType.Back(onBackClick = onBackClick)
)
