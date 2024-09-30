package com.arjunjadeja.texty.base

import com.arjunjadeja.texty.DisplayStyle
import com.arjunjadeja.texty.ListDisplayStyle
import com.arjunjadeja.texty.Utility

typealias ClickHandler = () -> Unit
typealias DisplayStyleHandler = (displayStyle: DisplayStyle) -> Unit
typealias UtilityHandler = (utility: Utility) -> Unit
typealias ListDisplayStyleHandler = (listDisplayStyle: ListDisplayStyle) -> Unit
