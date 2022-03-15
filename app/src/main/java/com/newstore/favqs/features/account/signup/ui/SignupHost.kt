package com.newstore.favqs.features.account.signup.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.compose.dialog.ErrorDialog
import com.newstore.compose.layout.LoadingBox
import com.newstore.favqs.features.account.signup.ui.SignupViewModel.Action
import com.newstore.favqs.features.quote.list.ui.QuoteListHost
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.navigate

val SignupHost = Host("SignupHost")

@Composable
fun SignupHost(viewModel: SignupViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    val usernameError = viewModel.usernameError.observeAsState().value
    val emailError = viewModel.emailError.observeAsState().value
    val passwordError = viewModel.passwordError.observeAsState().value
    val passwordAgainError = viewModel.passwordAgainError.observeAsState().value

    val action = viewModel.action.observeAsState().value
    val isLoading = viewModel.isLoading.observeAsState().value ?: false

    when (action) {
        is Action.NavigateToList -> {
            navController.navigate(QuoteListHost)
        }
        is Action.ShowError -> {
            ErrorDialog(
                title = SignUpTexts.ERROR_DIALOG_TITLE,
                message = action.message,
                onClosed = { viewModel.resetAction() }
            )
        }
        else -> Unit
    }

    LoadingBox(isLoading = isLoading) {
        SignupScreen(
            onUserNameEntered = { viewModel.onUsernameChanged(it) },
            usernameError = usernameError,
            onEmailEntered = { viewModel.onEmailChanged(it) },
            emailError = emailError,
            onPasswordEntered = { viewModel.onPasswordChanged(it) },
            passwordError = passwordError,
            onPasswordAgainEntered = { viewModel.onPasswordAgainChanged(it) },
            passwordAgainError = passwordAgainError,
            onSignupRequest = { viewModel.startSignup() },
            onBackNavigation = { navController.popBackStack() }
        )
    }

}