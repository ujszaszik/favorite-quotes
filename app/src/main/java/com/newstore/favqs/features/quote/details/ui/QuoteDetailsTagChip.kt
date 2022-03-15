package com.newstore.favqs.features.quote.details.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.newstore.compose.resources.Colors
import com.newstore.compose.resources.Dimens

@Composable
fun QuoteDetailsTagChip(
    tag: String,
    onTagClicked: (String) -> Unit
) {

    Button(
        modifier = Modifier.padding(horizontal = Dimens.paddingHalf),
        shape = RoundedCornerShape(Dimens.buttonCornerRadius),
        colors = ButtonDefaults.buttonColors(backgroundColor = Colors.titleBlue),
        onClick = { onTagClicked(tag) },
        contentPadding = Dimens.paddingDefaultValues
    ) { Text(tag, color = Colors.white) }
}