package com.newstore.favqs.features.account.signup.ui

import androidx.lifecycle.ViewModel
import com.newstore.favqs.coroutines.*
import com.newstore.favqs.features.account.AccountRepository
import com.newstore.favqs.features.account.signup.model.SignupModel
import com.newstore.favqs.features.account.validation.EmailValidator
import com.newstore.favqs.features.account.validation.PasswordValidator
import com.newstore.favqs.features.account.validation.UserNameValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val repository: AccountRepository) : ViewModel() {

    val usernameInput = InputFlow { UserNameValidator.isValid(it) }
    val emailInput = InputFlow { EmailValidator.isValid(it) }
    val passwordInput = InputFlow { PasswordValidator.isValid(it) }
    val passwordAgainInput = InputFlow { it == passwordInput.actualValue() }

    private val _signUpError = mutableStateFlow<String>()
    val signUpError: StateFlow<String?> = _signUpError

    private val _isSignUpSuccessful = SingleEventFlow<Boolean>()
    val isSignUpSuccessful: Flow<Boolean?> = _isSignUpSuccessful.eventFlow

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    internal fun startSignup() {
        if (areAllFieldsValid()) {

            ResourceFlowMediator<SignupModel>(
                source = repository.signupUser(
                    username = usernameInput.actualValue(),
                    email = emailInput.actualValue(),
                    password = passwordInput.actualValue()
                ),
                viewModel = this,
                loadingFlow = _isLoading,
                onSuccess = { emitEvent(_isSignUpSuccessful, true) },
                onError = { emitValue(_signUpError, it) }
            ).begin()
        }
    }

    private fun areAllFieldsValid() =
        areAllValid(usernameInput, emailInput, passwordInput, passwordAgainInput)

    internal fun onUsernameChanged(newValue: String) = usernameInput.onValueChanged(newValue)

    internal fun onEmailChanged(newValue: String) = emailInput.onValueChanged(newValue)

    internal fun onPasswordChanged(newValue: String) = passwordInput.onValueChanged(newValue)

    internal fun onPasswordAgainChanged(newValue: String) =
        passwordAgainInput.onValueChanged(newValue)

    internal fun clearSignupError() = _signUpError.clear()
}