package com.newstore.favqs.features.account.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newstore.extension.empty
import com.newstore.favqs.data.ResourceFlowMediator
import com.newstore.favqs.features.account.AccountRepository
import com.newstore.favqs.features.account.validation.PasswordValidator
import com.newstore.favqs.features.account.validation.UserNameValidator
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private var username = String.empty
    private val _usernameError = MutableLiveData<String?>()
    val usernameError: LiveData<String?> = _usernameError

    private var password = String.empty
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    internal fun startLogin() {
        if (isValidUsername() && isValidPassword()) {

            val source = repository.loginUser(username, password)

            ResourceFlowMediator(
                viewModel = this,
                source = source,
                action = _action,
                loading = _isLoading,
                emitOnSuccess = {
                    settingsManager.saveUserToken(it.token)
                    settingsManager.saveUserName(username)
                    Action.NavigateToList
                },
                emitOnError = { Action.ShowError(it) }
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
        if (!isValidUsername) _usernameError.postValue(UserNameValidator.ERROR_MESSAGE)
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
        if (!isValidPassword) _passwordError.postValue(PasswordValidator.ERROR_MESSAGE)
        return isValidPassword
    }

    internal fun resetAction() {
        _action.value = null
    }

    sealed class Action {
        object NavigateToList : Action()
        class ShowError(val message: String) : Action()
    }

}