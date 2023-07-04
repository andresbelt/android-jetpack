package com.platzi.android.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val PlatziBackgroundDark = Color(0xff273B47)
val PlatziBackgroundLight = Color(0xFF7E96A5)
val PlatziPrimary = Color(0xff98CA3F)
val TextDetail = Color(0xFFF3F8FB)

val Colors.topAppBarContentColor: Color
    get() = if (isLight) Color.White else Color.LightGray

val Colors.topAppBarBackgroundColor: Color
    get() = if (isLight) PlatziBackgroundLight else PlatziBackgroundDark
