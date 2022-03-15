package com.newstore.compose.color

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

val random = Random(0)

fun Color.Companion.random(): Color {
    val red = random.nextInt(128)
    val green = random.nextInt(128)
    val blue = random.nextInt(128)
    return Color(red, green, blue, 255)
}