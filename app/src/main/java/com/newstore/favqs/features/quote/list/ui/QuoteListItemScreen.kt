package com.newstore.favqs.features.quote.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.newstore.compose.layout.CenteredColumn
import com.newstore.compose.layout.DoubleSpacer
import com.newstore.compose.resources.Colors
import com.newstore.compose.resources.Dimens
import com.newstore.compose.resources.Fonts
import com.newstore.extension.authored
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.list.model.QuoteModel
import com.newstore.favqs.features.quote.list.model.getAnnotatedQuote

@Composable
fun QuoteListItemScreen(
    modifier: Modifier = Modifier,
    quoteModel: QuoteModel,
    searchParams: QuoteSearchParams,
    onItemClick: (QuoteModel) -> Unit
) {

    Card(
        modifier = modifier.padding(Dimens.paddingHalf),
        shape = RoundedCornerShape(Dimens.cardCornerRadius),
        elevation = Dimens.cardElevation
    ) {

        CenteredColumn(modifier = Modifier
            .clip(RoundedCornerShape(Dimens.cardCornerRadius))
            .clickable { onItemClick.invoke(quoteModel) }
            .padding(horizontal = Dimens.paddingHalf)
        ) {

            DoubleSpacer()

            Text(
                text = quoteModel.getAnnotatedQuote(
                    searchParams.keyword,
                    searchParams.type.needsKeywordHighlight
                ),
                textAlign = TextAlign.Center,
                fontFamily = Fonts.quoteFamily,
                fontStyle = FontStyle.Italic,
                color = Colors.steelGray,
                fontSize = Dimens.highlightTextSize,
            )

            DoubleSpacer()

            Text(
                text = quoteModel.author!!.authored(),
                textAlign = TextAlign.Center,
                fontFamily = Fonts.quoteFamily,
                fontWeight = FontWeight.Bold,
                color = Colors.titleBlue,
                fontSize = Dimens.highlightTextSize,
            )

            DoubleSpacer()

        } // CenteredColumn

    } // Card
}