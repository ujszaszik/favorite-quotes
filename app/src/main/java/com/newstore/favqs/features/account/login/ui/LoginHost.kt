package com.newstore.favqs.features.account.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.newstore.compose.dialog.ErrorDialog
import com.newstore.compose.layout.LoadingBox
import com.newstore.favqs.coroutines.collectAsStateValue
import com.newstore.favqs.features.quote.list.ui.QuoteListHost
import com.newstore.favqs.features.quote.quoteoftheday.ui.QuoteOfTheDayHost
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.navigate

val LoginHost = Host("LoginHost")

fun NavController.navigateToLogin() {
    navigate(LoginHost.route) {
        popUpTo(QuoteOfTheDayHost.route)
    }
}

@Composable
fun LoginHost(viewModel: LoginViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    val isLoading = viewModel.isLoading.collectAsState().value ?: false
    val isLoginSuccessful = viewModel.isLoginSuccessful.collectAsState(null).value

    val usernameError = viewModel.usernameInput.collectErrorState()
    val passwordError = viewModel.passwordInput.collectErrorState()
    val loginError = viewModel.loginError.collectAsStateValue()

    loginError?.let { errorMessage ->
        ErrorDialog(
            title = LoginTexts.ERROR_DIALOG_TITLE,
            message = errorMessage,
            onClosed = { viewModel.clearLoginError() }
        )
    }

    LaunchedEffect(isLoginSuccessful) {
        if (true == isLoginSuccessful) navController.navigate(QuoteListHost)
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