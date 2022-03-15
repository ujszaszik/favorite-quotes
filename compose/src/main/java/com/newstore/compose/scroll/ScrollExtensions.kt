package com.newstore.compose.scroll

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.enableVerticalScroll(): Modifier = composed {
    return@composed verticalScroll(rememberScrollState())
}