package com.newstore.compose.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.newstore.compose.resources.Colors
import com.newstore.compose.resources.Dimens
import com.newstore.extension.empty

@Composable
fun Counter(
    value: Number,
    textColor: Color,
    icon: ImageVector,
    iconTint: Color,
    onClick: () -> Unit = {}
) {

    Button(
        modifier = Modifier.padding(horizontal = Dimens.paddingDouble),
        shape = RoundedCornerShape(Dimens.buttonCornerRadius),
        colors = ButtonDefaults.buttonColors(backgroundColor = Colors.white),
        onClick = onClick,
        contentPadding = Dimens.paddingDefaultValues
    ) {
        Icon(
            icon,
            tint = iconTint,
            contentDescription = String.empty,
            modifier = Modifier.size(Dimens.defaultIconSize)
        )

        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

        Text(
            text = value.toString(),
            color = textColor, fontSize = Dimens.counterTextSize,
            fontWeight = FontWeight.Bold
        )
    }
}