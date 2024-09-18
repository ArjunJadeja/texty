package com.arjunjadeja.texty.design_system.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.base.ClickHandler
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingBig
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingMedium
import com.arjunjadeja.texty.design_system.properties.AppDimens.paddingSmall
import com.arjunjadeja.texty.design_system.properties.JetbrainsMonoFont
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get
import kotlinx.coroutines.delay

@Composable
fun CopyableCodeBlock(
    code: String,
    modifier: Modifier = Modifier
) {
    val clipboardManager = LocalClipboardManager.current
    var isCopied by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        TopSection(isCopied = isCopied) {
            clipboardManager.setText(AnnotatedString(code))
            isCopied = true
        }
        CodeContainer(code = code)
    }

    LaunchedEffect(isCopied) {
        if (isCopied) {
            delay(600)
            isCopied = false
        }
    }
}

@Composable
private fun TopSection(isCopied: Boolean, onCopyClicked: ClickHandler) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .padding(horizontal = paddingMedium, vertical = paddingSmall),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Texty(text = "Code", textStyle = TextyStyle.CODE_LABEL.get())
    Button(
        onClick = { onCopyClicked() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Texty(
            text = if (isCopied) "Copied!" else "Copy",
            textStyle = TextyStyle.COPY_BUTTON_STYLE.get()
        )
    }
}

@Composable
private fun CodeContainer(code: String) = SelectionContainer {
    Texty(
        text = code,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.primary,
            fontFamily = JetbrainsMonoFont()
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = paddingBig)
    )
}
