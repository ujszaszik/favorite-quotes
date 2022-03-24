package com.newstore.favqs.features.account.signup.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newstore.extension.empty
import com.newstore.favqs.data.ResourceFlowMediator
import com.newstore.favqs.features.account.AccountRepository
import com.newstore.favqs.features.account.validation.EmailValidator
import com.newstore.favqs.features.account.validation.PasswordValidator
import com.newstore.favqs.features.account.validation.UserNameValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: AccountRepository
) : ViewModel() {

    private var username = String.empty
    private val _usernameError = MutableLiveData<String?>()
    val usernameError: LiveData<String?> = _usernameError

    private var email = String.empty
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private var password = String.empty
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private var passwordAgain = String.empty
    private val _passwordAgainError = MutableLiveData<String?>()
    val passwordAgainError: LiveData<String?> = _passwordAgainError

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    internal fun startSignup() {
        if (areAllFieldsValid()) {

            val source = repository.signupUser(username, email, password)

            ResourceFlowMediator(
                viewModel = this,
                source = source,
                action = _action,
                loading = _isLoading,
                emitOnSuccess = { Action.NavigateToList },
                emitOnError = { Action.ShowError(it) }
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
        if (!isValidUsername) _usernameError.postValue(UserNameValidator.ERROR_MESSAGE)
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
        if (!isValidEmail) _emailError.postValue(EmailValidator.ERROR_MESSAGE)
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
        if (!isValidPassword) _passwordError.postValue(PasswordValidator.ERROR_MESSAGE)
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
        if (!arePasswordsMatch) _passwordAgainError.postValue(PasswordValidator.UNMATCHED_ERROR_MESSAGE)
        return arePasswordsMatch
    }

    internal fun resetAction() {
        _action.value = null
    }

    sealed class Action {
        object NavigateToList : Action()
        class ShowError(val message: String) : Action()
    }
}