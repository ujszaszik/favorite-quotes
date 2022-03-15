package com.newstore.compose.dialog

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

open class QuoteAlertDialogStyle {
    val cornerRadius = 16.dp

    val padding = PaddingValues(
        top = 48.dp,
        bottom = 32.dp,
        start = 16.dp,
        end = 16.dp
    )

    val spacing = 24.dp
}