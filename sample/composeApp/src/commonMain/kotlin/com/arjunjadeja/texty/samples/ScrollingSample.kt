package com.arjunjadeja.texty.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.Repeat
import com.arjunjadeja.texty.ScrollingDirection
import com.arjunjadeja.texty.Texty
import com.arjunjadeja.texty.design_system.components.SampleCard
import com.arjunjadeja.texty.design_system.components.SampleWrappingBox
import com.arjunjadeja.texty.design_system.properties.TextyStyle
import com.arjunjadeja.texty.design_system.properties.get

@Composable
fun ScrollingSample(isDemo: Boolean) = SampleCard(
    title = "Movie End Credits",
    description = "Sample demonstrating scrolling display style using movie end credits"
) {
    SampleWrappingBox(isDemo = isDemo) {
        var showMovieName by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            if (showMovieName) {
                Texty(
                    text = "The Quantum Paradox",
                    color = { Color.White },
                    textStyle = TextyStyle.DISPLAY_CARD_TITLE.get()
                )
            } else {
                Texty(
                    text = credits,
                    displayStyle = DisplayStyle.Scrolling(
                        scrollingDirection = ScrollingDirection.TowardsTop,
                        scrollDuration = 30_000L,
                        repeat = Repeat.Once,
                        onComplete = { showMovieName = true }
                    ),
                    color = { Color.White },
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        lineHeight = 1.6.em
                    )
                )
            }
        }
    }
}

private val credits by lazy {
    """
        The Quantum Paradox
        
        Directed by
        Sarah Johnson
        
        Written by
        Michael Chen
        Emily Rodríguez
        
        Produced by
        David Thompson
        
        Starring
        Emma Stone
        Ryan Gosling
        Lupita Nyong'o
        Idris Elba
        
        Cinematography by
        Roger Deakins
        
        Music by
        Hans Zimmer
        
        Edited by
        Thelma Schoonmaker
        
        Production Design
        Eve Stewart
        
        Costume Design
        Sandy Powell
        
        Visual Effects Supervisor
        John Dykstra
        
        Sound Design
        Ren Klyce
        
        Casting by
        Francine Maisler
        
        Executive Producers
        Christopher Nolan
        Emma Thomas
        
        A Paradox Films Production
        
        Special Thanks
        The City of Tokyo
        Quantum Physics Research Institute
        
        No paradoxes were harmed in the making of this film.
        
        © 2024 Paradox Films. All Rights Reserved.
    """.trimIndent()
}