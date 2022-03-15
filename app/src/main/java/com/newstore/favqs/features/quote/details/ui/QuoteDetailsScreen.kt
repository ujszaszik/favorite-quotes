package com.newstore.favqs.features.quote.details.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.newstore.compose.layout.CenteredRow
import com.newstore.compose.layout.DefaultSpacer
import com.newstore.compose.layout.LoadingBox
import com.newstore.compose.layout.TopCenterColumn
import com.newstore.compose.resources.Dimens
import com.newstore.compose.separator.VerticalSeparator
import com.newstore.favqs.features.quote.details.model.QuoteDetailsModel

@Composable
fun QuoteDetailsScreen(
    isLoading: Boolean,
    quoteDetails: QuoteDetailsModel,
    onTagClicked: (String) -> Unit
) {

    LoadingBox(
        modifier = Modifier.fillMaxSize(),
        isLoading = isLoading,
    ) {

        TopCenterColumn {

            QuoteDetailsText(
                content = quoteDetails.content!!,
                author = quoteDetails.author!!
            )

            DefaultSpacer()

            VerticalSeparator()

            FlowRow(
                mainAxisAlignment = FlowMainAxisAlignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.paddingDefault, horizontal = Dimens.paddingDouble)
            ) {

                if (quoteDetails.tags.isEmpty()) {
                    Text(QuoteDetailsTexts.EMPTY_TAGS)
                } else {
                    quoteDetails.tags.forEach { tag ->
                        QuoteDetailsTagChip(
                            tag = tag,
                            onTagClicked = { onTagClicked(tag) }
                        )
                    }
                }
            }

            DefaultSpacer()

            VerticalSeparator()

            CenteredRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.paddingDefault, horizontal = Dimens.paddingDouble)
            ) {
                UpVoteCounter(value = quoteDetails.upVotesCount)
                DownVoteCounter(value = quoteDetails.downVotesCount)
                FavoriteCounter(value = quoteDetails.favoritesCount)
            }

        }

    }

}