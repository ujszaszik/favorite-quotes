package com.newstore.favqs.features.account.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(settingsManager: SettingsManager) : ViewModel() {

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _isLoading.postValue(true)
        val username = settingsManager.getCurrentUserName()
        _isLoading.postValue(false)
        _action.postValue(Action.ShowProfileData(username))
    }

    sealed class Action {
        class ShowProfileData(val username: String) : Action()
    }
}