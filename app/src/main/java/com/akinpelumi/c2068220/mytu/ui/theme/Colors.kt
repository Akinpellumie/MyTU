package com.akinpelumi.c2068220.mytu.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
@Immutable
data class CustomColorsPalette(
    val primaryColor: Color = Color(0xFFDF6B0B),
    val altIconColor: Color = Color(0xFFF0B073),
    val linkTextColor: Color = Color(0xFF427BB5),
    val borderColor: Color = Color(0xFFD2D2D2),
    val eventBorderColor: Color = Color(0xFFD9D9D9),
    val textColor: Color = Color(0xFF000000),
    val descColor: Color = Color(0xFF424549),
    val itemBgColor: Color = Color(0xFFF8F9FA),
    val iconBgColor: Color = Color(0xFF6C757D),
    val white: Color = Color(0xFFFFFFFF)
)
val MaterialTheme.customColorsPalette: CustomColorsPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColorsPalette.current

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)