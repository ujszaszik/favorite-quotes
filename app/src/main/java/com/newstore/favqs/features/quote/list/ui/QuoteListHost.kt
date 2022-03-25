package com.newstore.favqs.features.quote.list.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.compose.dialog.QuoteAlertDialog
import com.newstore.compose.progress.ProgressBar
import com.newstore.favqs.coroutines.collectAsStateValue
import com.newstore.favqs.features.main.util.ActionBarChannel
import com.newstore.favqs.features.main.util.ActionBarEvent
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.details.ui.QuoteDetailsHost
import com.newstore.favqs.features.quote.list.ui.QuoteListViewModel.Action
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.host.HostType
import com.newstore.favqs.navigation.host.acceptParam
import com.newstore.favqs.navigation.host.withData
import com.newstore.favqs.navigation.navigate

const val QUOTE_SEARCH_KEY = "QuoteListHost::QuoteSearch"

val QuoteListHost = Host(
    route = "QuoteListHost",
    type = HostType.MAIN,
    showSearchBar = true
).acceptParam(QUOTE_SEARCH_KEY)

@Composable
fun QuoteListHost(
    searchParams: QuoteSearchParams,
    viewModel: QuoteListViewModel = hiltViewModel()
) {

    val navController = LocalNavController.current

    val isLoading = viewModel.isLoading.collectAsStateValue() ?: false
    val isRefreshing = viewModel.isRefreshing.collectAsStateValue() ?: false

    var showSearchView by remember { mutableStateOf(false) }

    val actionBarEvent = ActionBarChannel.receive().collectAsStateValue()

    showSearchView = actionBarEvent == ActionBarEvent.SearchRequested

    if (!viewModel.isInitialized) {
        viewModel.loadMoreItems(searchParams)
        viewModel.isInitialized = true
    }

    when (val action = viewModel.action.collectAsStateValue()) {
        is Action.ShowQuotesList -> {
            QuoteListScreen(
                isLoading = isLoading,
                hasFinishedLoading = action.finishedLoading,
                searchParams = searchParams,
                quotes = action.quotes,
                onQuoteClicked = { quote ->
                    navController.navigate(QuoteDetailsHost.withData(quote.id))
                },
                showSearchView = showSearchView,
                onSearchRequest = { params ->
                    ActionBarChannel.toggleSearchView()
                    navController.navigate(QuoteListHost.withData(params))
                },
                isRefreshing = isRefreshing && searchParams.isDefault,
                onRefresh = { if (searchParams.isDefault) navController.navigate(QuoteListHost) },
                onLoadMore = { viewModel.loadMoreItems(searchParams) },
                onFilterReset = { navController.navigate(QuoteListHost) }
            )
        }
        is Action.ShowError -> {
            QuoteAlertDialog(
                title = action.message,
                onOkay = { viewModel.resetPaging() }
            )
        }
        else -> ProgressBar()
    }
}