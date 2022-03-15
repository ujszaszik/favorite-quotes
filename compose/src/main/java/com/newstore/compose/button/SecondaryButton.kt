package com.newstore.compose.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.newstore.compose.resources.Colors

@Composable
fun SecondaryButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    DefaultButton(
        label = label,
        backgroundColor = Colors.white,
        textColor = Colors.titleBlue,
        icon = icon,
        iconTint = Colors.titleBlue,
        onClick = onClick
    )
}