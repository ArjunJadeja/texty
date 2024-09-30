package com.arjunjadeja.texty.design_system.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import texty.sample.composeapp.generated.resources.Res
import texty.sample.composeapp.generated.resources.indie_flower_regular
import texty.sample.composeapp.generated.resources.inter_variable
import texty.sample.composeapp.generated.resources.inter_variable_italic

@Composable
fun InterFont() = FontFamily(Font(Res.font.inter_variable))

@Composable
fun InterFontItalic() = FontFamily(Font(Res.font.inter_variable_italic))

@Composable
fun JetbrainsMonoFont() = FontFamily(Font(Res.font.inter_variable))

@Composable
fun JetbrainsMonoFontItalic() = FontFamily(Font(Res.font.inter_variable_italic))

@Composable
fun IndieFlowerFont() = FontFamily(Font(Res.font.indie_flower_regular))
