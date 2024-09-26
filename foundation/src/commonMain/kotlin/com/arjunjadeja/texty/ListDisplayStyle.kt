package com.arjunjadeja.texty

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface ListDisplayStyle {

    data class Motion(
        val delayBeforeNext: Long = 80L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Motion"
    }

    data class OneByOne(
        val transitionStyle: TransitionStyle = TransitionStyle.BASIC,
        val displayDuration: Long = 200L,
        val transitionInDuration: Long = 100L,
        val transitionOutDuration: Long = 100L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "One By One"
    }

    data class SlidingList(
        val separator: String? = null,
        val direction: SlidingDirection = SlidingDirection.TOWARDS_START,
        val duration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Sliding List"
    }

    data class ScrollingList(
        val spacing: Dp = 4.dp,
        val direction: ScrollingDirection = ScrollingDirection.TOWARDS_TOP,
        val duration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Scrolling List"
    }

}