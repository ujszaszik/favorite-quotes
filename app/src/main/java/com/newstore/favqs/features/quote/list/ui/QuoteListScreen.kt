package com.newstore.favqs.features.quote.list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.newstore.compose.layout.CenteredColumn
import com.newstore.compose.paging.PagingColumn
import com.newstore.compose.progress.ProgressBar
import com.newstore.compose.resources.Dimens
import com.newstore.compose.text.SearchView
import com.newstore.favqs.features.main.util.LocalKeyboardManager
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.QuoteSearchType
import com.newstore.favqs.features.quote.list.model.QuoteModel

@Composable
fun QuoteListScreen(
    isLoading: Boolean,
    hasFinishedLoading: Boolean,
    searchParams: QuoteSearchParams,
    quotes: List<QuoteModel>,
    onQuoteClicked: (QuoteModel) -> Unit,
    onLoadMore: () -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onSearchRequest: (QuoteSearchParams) -> Unit,
    onFilterReset: () -> Unit,
    showSearchView: Boolean,
) {

    val keyboardManager = LocalKeyboardManager.current

    CenteredColumn {

        if (showSearchView) {
            SearchView {
                keyboardManager.hide()
                val params = QuoteSearchParams(QuoteSearchType.KEYWORD, it)
                onSearchRequest(params)
            }
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { onRefresh() },
        ) {

            Column {

                searchParams.keyword?.let {
                    QuoteFilterHeader(
                        searchParams = searchParams,
                        onFilterReset = { onFilterReset() })
                }

                PagingColumn(
                    modifier = Modifier
                        .padding(horizontal = Dimens.paddingHalf)
                        .padding(top = Dimens.paddingDouble),
                    items = quotes,
                    itemContent = { item, modifier ->
                        QuoteListItemScreen(
                            modifier = modifier,
                            searchParams = searchParams,
                            quoteModel = item,
                            onItemClick = { onQuoteClicked(it) }
                        )
                    },
                    emptyContent = { QuoteListEmptyScreen() },
                    loadingContent = { ProgressBar() },
                    currentlyLoading = isLoading,
                    finishedLoading = hasFinishedLoading,
                    onLoadMore = onLoadMore
                )
            }
        }
    }

}