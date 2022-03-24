package com.newstore.favqs.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _onBackPressed = MutableLiveData<Boolean>()
    val onBackPressed: LiveData<Boolean> = _onBackPressed

    private val _onExitRequest = MutableLiveData<Boolean>()
    val onExitRequest: LiveData<Boolean> = _onExitRequest

    internal fun onBackPressed() = _onBackPressed.postValue(true)

    internal fun resetBackPress() {
        _onBackPressed.value = null
    }

    internal fun onExitRequest() = _onExitRequest.postValue(true)

    internal fun resetExitRequest() {
        _onExitRequest.value = null
    }

}