package com.newstore.favqs.features.main

import androidx.lifecycle.ViewModel
import com.newstore.favqs.coroutines.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _onBackPressed = mutableStateFlow<Boolean>()
    val onBackPressed: StateFlow<Boolean?> = _onBackPressed

    private val _onExitRequest = SingleEventFlow<Boolean>()
    val onExitRequest: Flow<Boolean?> = _onExitRequest.eventFlow

    internal fun onBackPressed() = emitValue(_onBackPressed, true)

    internal fun resetBackPress() = _onBackPressed.clear()

    internal fun onExitRequest() = emitEvent(_onExitRequest, true)

    internal fun resetExitRequest() = launch { _onExitRequest.clear() }
}