package com.newstore.favqs.features.account.signup.ui

import androidx.lifecycle.ViewModel
import com.newstore.extension.empty
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
class SignupViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {

    private var username = String.empty
    private val _usernameError = mutableStateFlow<String>()
    val usernameError: StateFlow<String?> = _usernameError

    private var email = String.empty
    private val _emailError = mutableStateFlow<String>()
    val emailError: StateFlow<String?> = _emailError

    private var password = String.empty
    private val _passwordError = mutableStateFlow<String>()
    val passwordError: StateFlow<String?> = _passwordError

    private var passwordAgain = String.empty
    private val _passwordAgainError = mutableStateFlow<String>()
    val passwordAgainError: StateFlow<String?> = _passwordAgainError

    private val _signUpError = mutableStateFlow<String>()
    val signUpError: StateFlow<String?> = _signUpError

    private val _isSignUpSuccessful = SingleEventFlow<Boolean>()
    val isSignUpSuccessful: Flow<Boolean?> = _isSignUpSuccessful.eventFlow

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    internal fun startSignup() {
        if (areAllFieldsValid()) {

            ResourceFlowMediator<SignupModel>(
                source = repository.signupUser(username, email, password),
                viewModel = this,
                loadingFlow = _isLoading,
                onSuccess = { emitEvent(_isSignUpSuccessful, true) },
                onError = { emitValue(_signUpError, it) }
            ).begin()
        }
    }

    private fun areAllFieldsValid(): Boolean {
        return isValidUsername() && isValidEmail() && isValidPassword() && arePasswordsMatch()
    }

    internal fun onUsernameChanged(newValue: String) {
        if (newValue != username) {
            username = newValue
            _usernameError.value = null
        }
    }

    private fun isValidUsername(): Boolean {
        val isValidUsername = UserNameValidator.isValid(username)
        if (!isValidUsername) emitValue(_usernameError, UserNameValidator.ERROR_MESSAGE)
        return isValidUsername
    }

    internal fun onEmailChanged(newValue: String) {
        if (newValue != email) {
            email = newValue
            _emailError.value = null
        }
    }

    private fun isValidEmail(): Boolean {
        val isValidEmail = EmailValidator.isValid(email)
        if (!isValidEmail) emitValue(_emailError, EmailValidator.ERROR_MESSAGE)
        return isValidEmail
    }

    internal fun onPasswordChanged(newValue: String) {
        if (newValue != password) {
            password = newValue
            _passwordError.value = null
        }
    }

    private fun isValidPassword(): Boolean {
        val isValidPassword = PasswordValidator.isValid(password)
        if (!isValidPassword) emitValue(_passwordError, PasswordValidator.ERROR_MESSAGE)
        return isValidPassword
    }

    internal fun onPasswordAgainChanged(newValue: String) {
        if (newValue != passwordAgain) {
            passwordAgain = newValue
            _passwordAgainError.value = null
        }
    }

    private fun arePasswordsMatch(): Boolean {
        val arePasswordsMatch = password == passwordAgain
        if (!arePasswordsMatch) {
            emitValue(_passwordAgainError, PasswordValidator.UNMATCHED_ERROR_MESSAGE)
        }
        return arePasswordsMatch
    }

    internal fun clearSignupError() = _signUpError.clear()
}