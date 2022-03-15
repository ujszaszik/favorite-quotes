package com.newstore.favqs.features.main.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    val isSplashFinished = MutableLiveData<Boolean>()

    init {
        makeSplashDelay()
    }

    private fun makeSplashDelay() {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            isSplashFinished.value = true
        }
    }

    companion object {
        private const val SPLASH_DELAY = 1500L
    }
}