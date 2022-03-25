package com.newstore.favqs.features.account.profile

import androidx.lifecycle.ViewModel
import com.newstore.favqs.coroutines.emitValue
import com.newstore.favqs.coroutines.mutableStateFlow
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(settingsManager: SettingsManager) : ViewModel() {

    private val _currentUserName = mutableStateFlow<String>()
    val currentUserName: StateFlow<String?> = _currentUserName

    private val _isLoading by lazy { mutableStateFlow<Boolean>() }
    val isLoading: StateFlow<Boolean?> = _isLoading

    init {
        emitValue(_isLoading, true)
        val username = settingsManager.getCurrentUserName()
        emitValue(_isLoading, false)
        emitValue(_currentUserName, username)
    }
}