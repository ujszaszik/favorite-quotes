package com.newstore.favqs.features.quote.quoteoftheday.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.newstore.compose.layout.CenteredColumn
import com.newstore.compose.layout.DoubleSpacer
import com.newstore.compose.layout.LargeSpacer
import com.newstore.compose.resources.Colors
import com.newstore.compose.resources.Dimens
import com.newstore.compose.resources.Fonts
import com.newstore.compose.resources.Strings
import com.newstore.extension.authored
import com.newstore.extension.quoted

@Composable
fun LargeQuoteText(content: String, author: String) {
    CenteredColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.paddingDouble)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.paddingDefault),
            text = Strings.QUOTE_OF_THE_DAY_TITLE,
            textAlign = TextAlign.Center,
            fontFamily = Fonts.titleFamily,
            color = Colors.titleBlue,
            fontSize = Dimens.titleTextSize,
        )

        LargeSpacer()

        Text(
            text = content.quoted(),
            textAlign = TextAlign.Center,
            fontFamily = Fonts.quoteFamily,
            fontStyle = FontStyle.Italic,
            color = Colors.steelGray,
            fontSize = Dimens.detailsQuoteTextSize
        )

        DoubleSpacer()

        Text(
            text = author.authored(),
            textAlign = TextAlign.Center,
            fontFamily = Fonts.quoteFamily,
            fontWeight = FontWeight.Bold,
            color = Colors.titleBlue,
            fontSize = Dimens.detailsAuthorTextSize,
        )
    } // CenteredColumn

}