package com.newstore.favqs.features.account.logout.ui

import androidx.lifecycle.ViewModel
import com.newstore.favqs.coroutines.*
import com.newstore.favqs.features.account.AccountRepository
import com.newstore.favqs.features.account.logout.model.LogoutModel
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _isLogoutSuccessful = SingleEventFlow<Boolean>()
    val isLogoutSuccessful: Flow<Boolean?> = _isLogoutSuccessful.eventFlow

    private val _logoutError = mutableStateFlow<String>()
    val logoutError: StateFlow<String?> = _logoutError

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    internal fun onLogoutRequest() {
        ResourceFlowMediator<LogoutModel>(
            source = repository.logoutCurrentUser(),
            viewModel = this,
            loadingFlow = _isLoading,
            onSuccess = {
                settingsManager.clearUserToken()
                emitEvent(_isLogoutSuccessful, true)
            },
            onError = { emitValue(_logoutError, it) }
        ).begin()
    }
}