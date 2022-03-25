package com.newstore.favqs.features.account.login.ui

import androidx.lifecycle.ViewModel
import com.newstore.extension.empty
import com.newstore.favqs.coroutines.*
import com.newstore.favqs.features.account.AccountRepository
import com.newstore.favqs.features.account.login.model.LoginModel
import com.newstore.favqs.features.account.validation.PasswordValidator
import com.newstore.favqs.features.account.validation.UserNameValidator
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private var username = String.empty
    private val _usernameError = mutableStateFlow<String>()
    val usernameError: StateFlow<String?> = _usernameError

    private var password = String.empty
    private val _passwordError = mutableStateFlow<String>()
    val passwordError: StateFlow<String?> = _passwordError

    private val _loginError = mutableStateFlow<String>()
    val loginError: StateFlow<String?> = _loginError

    private val _isLoginSuccessful = SingleEventFlow<Boolean>()
    val isLoginSuccessful: Flow<Boolean?> = _isLoginSuccessful.eventFlow

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    internal fun startLogin() {
        if (isValidUsername() && isValidPassword()) {

            ResourceFlowMediator<LoginModel>(
                source = repository.loginUser(username, password),
                viewModel = this,
                loadingFlow = _isLoading,
                onSuccess = {
                    settingsManager.saveUserToken(it.token)
                    settingsManager.saveUserName(username)
                    emitEvent(_isLoginSuccessful, true)
                },
                onError = { emitValue(_loginError, it) }
            ).begin()
        }
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

    internal fun clearLoginError() {
        _loginError.value = null
    }

}