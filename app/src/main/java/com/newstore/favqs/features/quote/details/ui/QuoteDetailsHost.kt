package com.newstore.favqs.features.quote.details.ui

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.compose.dialog.QuoteAlertDialog
import com.newstore.favqs.features.quote.details.ui.QuoteDetailsViewModel.Action
import com.newstore.favqs.features.quote.list.ui.QuoteListHost
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.host.HostType
import com.newstore.favqs.navigation.host.acceptParam
import com.newstore.favqs.navigation.host.withData

const val QUOTE_ID_KEY = "QuoteDetailsHost::QuoteId"

val QuoteDetailsHost = Host(
    route = "QuoteDetailsHost",
    type = HostType.SUB,
).acceptParam(QUOTE_ID_KEY)

@Composable
fun QuoteDetailsHost(quoteId: Long?, viewModel: QuoteDetailsViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    val isLoading = viewModel.isLoading.observeAsState().value ?: false
    val action = viewModel.action.observeAsState().value

    var isAlreadyLoaded by remember { mutableStateOf(false) }

    quoteId?.let {

        if (!isAlreadyLoaded) {
            viewModel.loadQuoteDetails(it)
            isAlreadyLoaded = true
        }

        when (action) {
            is Action.ShowError -> {
                QuoteAlertDialog(
                    title = action.message,
                    onOkay = { navController.popBackStack() }
                )
            }
            is Action.ShowQuoteDetails -> {
                QuoteDetailsScreen(
                    isLoading = isLoading,
                    quoteDetails = action.quoteDetailsModel,
                    onTagClicked = { tag -> viewModel.onTagClicked(tag) }
                )
            }
            is Action.StartTagSearch -> {
                navController.navigate(QuoteListHost.withData(action.params))
            }
            else -> Unit
        }
    }


}