package com.newstore.favqs.features.quote.quoteoftheday.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.compose.dialog.QuoteAlertDialog
import com.newstore.compose.progress.ProgressBar
import com.newstore.favqs.features.account.login.ui.navigateToLogin
import com.newstore.favqs.features.quote.list.ui.QuoteListHost
import com.newstore.favqs.features.quote.quoteoftheday.model.QuoteOfTheDayModel
import com.newstore.favqs.features.quote.quoteoftheday.ui.QuoteOfTheDayViewModel.Action.*
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.navigate

val QuoteOfTheDayHost = Host("QuoteOfTheDayHost")

@Composable
fun QuoteOfTheDayHost(viewModel: QuoteOfTheDayViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    var quoteModel = remember { mutableStateOf<QuoteOfTheDayModel?>(null) }.value

    when (val action = viewModel.action.observeAsState().value) {
        is NavigateToLogin -> navController.navigateToLogin()
        is NavigateToList -> navController.navigate(QuoteListHost)
        is ShowQuote -> quoteModel = action.quoteModel
        is ShowError -> {
            QuoteAlertDialog(
                title = action.message,
                onOkay = { navController.navigateToLogin() }
            )
        }
        else -> Unit
    }

    if (quoteModel != null) {
        QuoteOfTheDayScreen(
            quote = quoteModel,
            onCheckChange = { isChecked -> viewModel.onCheckChange(isChecked) },
            onDismissed = { viewModel.onQuoteDismissed() }
        )
    } else ProgressBar()
}