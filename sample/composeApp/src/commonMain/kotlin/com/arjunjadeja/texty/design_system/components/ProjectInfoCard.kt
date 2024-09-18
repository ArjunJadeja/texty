package com.arjunjadeja.texty.design_system.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arjunjadeja.texty.Texty
import org.jetbrains.compose.resources.painterResource
import texty.sample.composeapp.generated.resources.Res
import texty.sample.composeapp.generated.resources.github

// Style reference card
@Composable
fun ProjectInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Texty(
                text = "Beautifully crafted text styles for composables",
                textStyle = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Texty(
                text = "100% FREE FOR COMMERCIAL AND PERSONAL USE",
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Texty(
                text = "UNDER Apache 2.0",
                textStyle = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                ),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Texty(
                text = "Kotlin Multiplatform & Compose Multiplatform",
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    painter = painterResource(resource = Res.drawable.github),
                    contentDescription = "GitHub Repository",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    painter = painterResource(resource = Res.drawable.github),
                    contentDescription = "Kotlin Repository",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    painter = painterResource(resource = Res.drawable.github),
                    contentDescription = "Jetpack Compose Repository",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    painter = painterResource(resource = Res.drawable.github),
                    contentDescription = "Kotlin Multiplatform",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Icon(
                    painter = painterResource(resource = Res.drawable.github),
                    contentDescription = "Compose Multiplatform",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Texty(
                text = "Use it for any platform",
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
