package com.newstore.compose.resources

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.newstore.compose.R

object Fonts {

    val titleFamily = FontFamily(
        Font(R.font.grandhotel_regular)
    )

    val quoteFamily = FontFamily(
        Font(R.font.poly_regular),
        Font(R.font.poly_italic, weight = FontWeight.Normal, style = FontStyle.Italic)
    )

}