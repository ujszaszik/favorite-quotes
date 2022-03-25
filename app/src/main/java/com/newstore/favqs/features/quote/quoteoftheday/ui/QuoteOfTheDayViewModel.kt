package com.newstore.favqs.features.quote.quoteoftheday.ui

import androidx.lifecycle.ViewModel
import com.newstore.favqs.coroutines.*
import com.newstore.favqs.features.quote.QuoteRepository
import com.newstore.favqs.features.quote.quoteoftheday.model.QuoteOfTheDayModel
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuoteOfTheDayViewModel @Inject constructor(
    private val repository: QuoteRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _action = mutableStateFlow<Action?>()
    val action: StateFlow<Action?> = _action

    private val _showLoginScreen = SingleEventFlow<Boolean>()
    val showLoginScreen: Flow<Boolean?> = _showLoginScreen.eventFlow

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    init {
        if (settingsManager.needsToShowQuoteOnStartup()) postQuoteOfTheDay()
        else launch { _showLoginScreen.emit(true) }
    }

    private fun postQuoteOfTheDay() {
        ResourceFlowMediator<QuoteOfTheDayModel>(
            source = repository.getQuoteOfTheDay(),
            viewModel = this,
            loadingFlow = _isLoading,
            onSuccess = { emitValue(_action, Action.ShowQuote(it)) },
            onError = { emitValue(_action, Action.ShowError(it)) }
        ).begin()
    }

    internal fun onCheckChange(newValue: Boolean) {
        settingsManager.setIfNeedsToShowQuotesOnStartup(newValue)
    }

    internal fun onQuoteDismissed() {
        launch { _showLoginScreen.emit(!settingsManager.hasUserToken()) }
    }

    sealed class Action {
        class ShowError(val message: String) : Action()
        class ShowQuote(val quoteModel: QuoteOfTheDayModel) : Action()
    }

}