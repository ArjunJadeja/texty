package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox

data class Message(val text: String, val isUser: Boolean)

@Composable
fun TypingSample(isDemo: Boolean) = SampleCard(
    title = "Chat Interface",
    description = "Sample demonstrating typing typing style using chat messages."
) {
    SampleWrappingBox(isDemo = isDemo) {
        var messages by remember {
            mutableStateOf(
                listOf(
                    Message("Hi there! How can I help you today?", false),
                    Message("I'm looking for information about Jetpack Compose.", true),
                    Message(
                        "Great! Jetpack Compose is a modern toolkit for building native Android UI. It simplifies and accelerates UI development on Android. Would you like me to explain more about its key features?",
                        false
                    )
                )
            )
        }
        var inputText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ChatHeader()
            ChatMessages(messages, Modifier.weight(1f))
            ChatInput(
                inputText = inputText,
                onInputChange = { inputText = it },
                onSendMessage = {
                    if (inputText.isNotBlank()) {
                        messages = messages + Message(inputText, true)
                        inputText = ""
                    }
                }
            )
        }
    }
}

@Composable
private fun ChatHeader() = Box(
    modifier = Modifier
        .fillMaxWidth()
        .background(
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0))
            )
        )
        .padding(16.dp)
) {
    Texty(
        text = "ChatApp",
        displayStyle = DisplayStyle.Basic(),
        color = { Color.White },
        textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
    )
}

@Composable
private fun ChatMessages(messages: List<Message>, modifier: Modifier = Modifier) = LazyColumn(
    modifier = modifier.fillMaxWidth(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(messages) { message ->
        ChatBubble(message)
    }
}

@Composable
private fun ChatBubble(message: Message) = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (message.isUser) Color(0xFF2196F3) else Color(0xFFE0E0E0)
            )
            .padding(12.dp)
    ) {
        Texty(
            text = message.text,
            displayStyle = DisplayStyle.Typing(typingDelayPerChar = 50L),
            color = { if (message.isUser) Color.White else Color.Black },
            textStyle = TextStyle(fontSize = 16.sp)
        )
    }
}

@Composable
private fun ChatInput(
    inputText: String,
    onInputChange: (String) -> Unit,
    onSendMessage: () -> Unit
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    TextField(
        value = inputText,
        onValueChange = onInputChange,
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(24.dp)),
        placeholder = {
            Texty(
                text = "Type a message...",
                displayStyle = DisplayStyle.Basic(),
                color = { Color.Gray }
            )
        }
    )
    Spacer(modifier = Modifier.width(8.dp))
    IconButton(
        onClick = onSendMessage,
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color(0xFF4A00E0))
    ) {
        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = Color.White)
    }
}
