package com.newstore.compose.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.newstore.compose.resources.Colors
import com.newstore.compose.resources.Dimens
import com.newstore.compose.resources.Fonts

@Composable
fun TitleText(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Dimens.paddingDefault),
        text = text,
        textAlign = TextAlign.Center,
        fontFamily = Fonts.titleFamily,
        color = Colors.titleBlue,
        fontSize = Dimens.titleTextSize,
    )
}