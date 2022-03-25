package com.newstore.favqs.features.quote.quoteoftheday.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.compose.dialog.QuoteAlertDialog
import com.newstore.compose.progress.ProgressBar
import com.newstore.favqs.coroutines.collectAsStateValue
import com.newstore.favqs.features.account.login.ui.navigateToLogin
import com.newstore.favqs.features.quote.list.ui.QuoteListHost
import com.newstore.favqs.features.quote.quoteoftheday.model.QuoteOfTheDayModel
import com.newstore.favqs.features.quote.quoteoftheday.ui.QuoteOfTheDayViewModel.Action.ShowError
import com.newstore.favqs.features.quote.quoteoftheday.ui.QuoteOfTheDayViewModel.Action.ShowQuote
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.BackPressStrategy
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.navigate

val QuoteOfTheDayHost = Host(
    route = "QuoteOfTheDayHost",
    backPressStrategy = BackPressStrategy.EXIT_APPLICATION
)

@Composable
fun QuoteOfTheDayHost(viewModel: QuoteOfTheDayViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    var quoteModel = remember { mutableStateOf<QuoteOfTheDayModel?>(null) }.value

    val showLogin = viewModel.showLoginScreen.collectAsStateValue()
    LaunchedEffect(showLogin) {
        showLogin?.let {
            if (it) navController.navigateToLogin()
            else navController.navigate(QuoteListHost)
        }
    }

    when (val action = viewModel.action.collectAsState().value) {
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