package com.newstore.favqs.features.account.login.ui

import androidx.lifecycle.ViewModel
import com.newstore.favqs.coroutines.*
import com.newstore.favqs.features.account.AccountRepository
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

    val usernameInput = InputFlow { UserNameValidator.isValid(it) }
    val passwordInput = InputFlow { PasswordValidator.isValid(it) }

    private val _loginError = mutableStateFlow<String>()
    val loginError: StateFlow<String?> = _loginError

    private val _isLoginSuccessful = SingleEventFlow<Boolean>()
    val isLoginSuccessful: Flow<Boolean?> = _isLoginSuccessful.eventFlow

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    internal fun startLogin() {
        if (areAllValid(usernameInput, passwordInput)) {

            ResourceFlowMediator(
                source = repository.loginUser(
                    username = usernameInput.actualValue(),
                    password = passwordInput.actualValue()
                ),
                viewModel = this,
                loadingFlow = _isLoading,
                onSuccess = {
                    settingsManager.saveUserToken(it.token)
                    settingsManager.saveUserName(usernameInput.actualValue())
                    emitEvent(_isLoginSuccessful, true)
                },
                onError = { emitValue(_loginError, it) }
            ).begin()
        }
    }

    internal fun onUsernameChanged(newValue: String) = usernameInput.onValueChanged(newValue)

    internal fun onPasswordChanged(newValue: String) = passwordInput.onValueChanged(newValue)

    internal fun clearLoginError() = _loginError.clear()

}