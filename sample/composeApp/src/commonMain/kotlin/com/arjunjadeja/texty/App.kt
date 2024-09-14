package com.arjunjadeja.texty

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arjunjadeja.texty.theme.AppTheme
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import texty.sample.composeapp.generated.resources.IndieFlower_Regular
import texty.sample.composeapp.generated.resources.Res
import texty.sample.composeapp.generated.resources.texty

@Composable
internal fun App() = AppTheme {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
    }
}

@Composable
private fun Title() {
    Texty(
        text = stringResource(Res.string.texty),
        displayStyle = DisplayStyle.Default,
        modifier = Modifier.padding(8.dp),
        textStyle = MaterialTheme.typography.displayLarge.copy(
            fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
            color = MaterialTheme.colorScheme.primary
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
