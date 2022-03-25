package com.newstore.favqs.features.account.signup.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.compose.dialog.ErrorDialog
import com.newstore.compose.layout.LoadingBox
import com.newstore.favqs.coroutines.collectAsStateValue
import com.newstore.favqs.features.quote.list.ui.QuoteListHost
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.navigate

val SignupHost = Host("SignupHost")

@Composable
fun SignupHost(viewModel: SignupViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    val usernameError = viewModel.usernameInput.collectErrorState()
    val emailError = viewModel.emailInput.collectErrorState()
    val passwordError = viewModel.passwordInput.collectErrorState()
    val passwordAgainError = viewModel.passwordAgainInput.collectErrorState()
    val signUpError = viewModel.signUpError.collectAsStateValue()

    val isSignupSuccessful = viewModel.isSignUpSuccessful.collectAsStateValue()
    val isLoading = viewModel.isLoading.collectAsStateValue() ?: false

    LaunchedEffect(isSignupSuccessful) {
        if (true == isSignupSuccessful) navController.navigate(QuoteListHost)
    }

    signUpError?.let {
        ErrorDialog(
            title = SignUpTexts.ERROR_DIALOG_TITLE,
            message = it,
            onClosed = { viewModel.clearSignupError() }
        )
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