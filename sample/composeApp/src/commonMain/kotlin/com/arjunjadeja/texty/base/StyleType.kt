package com.arjunjadeja.texty.base

import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Utility

sealed interface StyleType {
    data class DisplayStyleType(val displayStyle: DisplayStyle) : StyleType {
        override fun toString(): String = displayStyle.toString()
    }

    data class ListDisplayStyleType(val displayStyle: ListDisplayStyle) : StyleType {
        override fun toString(): String = displayStyle.toString()
    }

    data class UtilityType(val utility: Utility) : StyleType {
        override fun toString(): String = utility.toString()
    }
}