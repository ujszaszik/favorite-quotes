package com.newstore.favqs.features.account.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newstore.compose.dialog.ErrorDialog
import com.newstore.compose.layout.LoadingBox
import com.newstore.favqs.features.account.login.ui.LoginViewModel.Action.NavigateToList
import com.newstore.favqs.features.account.login.ui.LoginViewModel.Action.ShowError
import com.newstore.favqs.features.quote.list.ui.QuoteListHost
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.navigate

val LoginHost = Host("LoginHost")

fun NavController.navigateToLogin() {
    navigate(LoginHost.route) {
        popUpTo(LoginHost.route)
    }
}

@Composable
fun LoginHost(viewModel: LoginViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    val usernameError = viewModel.usernameError.observeAsState().value
    val passwordError = viewModel.passwordError.observeAsState().value

    val action = viewModel.action.observeAsState().value
    val isLoading = viewModel.isLoading.observeAsState().value ?: false

    when (action) {
        is NavigateToList -> navController.navigate(QuoteListHost)
        is ShowError -> {
            ErrorDialog(
                title = LoginTexts.ERROR_DIALOG_TITLE,
                message = action.message,
                onClosed = { viewModel.resetAction() }
            )
        }
        else -> Unit
    }

    LoadingBox(isLoading = isLoading) {
        LoginScreen(
            onUserNameEntered = { viewModel.onUsernameChanged(it) },
            usernameError = usernameError,
            onPasswordEntered = { viewModel.onPasswordChanged(it) },
            passwordError = passwordError,
            onLoginRequest = { viewModel.startLogin() }
        )
    }

}