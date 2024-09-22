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
        val transitionEffect: TransitionEffect = TransitionEffect.BASIC,
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
        val slidingDirection: SlidingDirection = SlidingDirection.TowardsStart,
        val slideDuration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Sliding List"
    }

    data class ScrollingList(
        val spacing: Dp = 4.dp,
        val scrollingDirection: ScrollingDirection = ScrollingDirection.TowardsTop,
        val scrollDuration: Long = 3000L,
        val repeat: Repeat = Repeat.Continuous,
        val onComplete: () -> Unit = {}
    ) : ListDisplayStyle {
        override fun toString() = "Scrolling List"
    }

}