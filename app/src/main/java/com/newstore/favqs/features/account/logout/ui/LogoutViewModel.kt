package com.newstore.favqs.features.account.logout.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newstore.favqs.data.ResourceFlowMediator
import com.newstore.favqs.features.account.AccountRepository
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> get() = _action

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    internal fun onLogoutRequest() {
        val source = repository.logoutCurrentUser()

        ResourceFlowMediator(
            viewModel = this,
            source = source,
            action = _action,
            loading = _isLoading,
            emitOnSuccess = {
                settingsManager.clearUserToken()
                Action.Logout
            },
            emitOnError = { Action.LogoutError(it) }
        ).begin()
    }

    sealed class Action {
        object Logout : Action()
        class LogoutError(val message: String) : Action()
    }
}