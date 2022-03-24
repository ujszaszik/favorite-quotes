package com.newstore.favqs.features.quote.quoteoftheday.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newstore.favqs.data.ResourceFlowMediator
import com.newstore.favqs.features.quote.QuoteRepository
import com.newstore.favqs.features.quote.quoteoftheday.model.QuoteOfTheDayModel
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteOfTheDayViewModel @Inject constructor(
    private val repository: QuoteRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    private val _showLoginScreen = MutableLiveData<Boolean>()
    val showLoginScreen: LiveData<Boolean> = _showLoginScreen

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        if (settingsManager.needsToShowQuoteOnStartup()) postQuoteOfTheDay()
        else _showLoginScreen.postValue(true)
    }

    private fun postQuoteOfTheDay() {
        val source = repository.getQuoteOfTheDay()

        ResourceFlowMediator(
            viewModel = this,
            source = source,
            action = _action,
            loading = _isLoading,
            emitOnSuccess = { Action.ShowQuote(it) },
            emitOnError = { Action.ShowError(it) }
        ).begin()
    }

    internal fun onCheckChange(newValue: Boolean) {
        settingsManager.setIfNeedsToShowQuotesOnStartup(newValue)
    }

    internal fun onQuoteDismissed() = _showLoginScreen.postValue(!settingsManager.hasUserToken())

    internal fun resetNavigation() {
        _showLoginScreen.value = null
    }

    sealed class Action {
        class ShowError(val message: String) : Action()
        class ShowQuote(val quoteModel: QuoteOfTheDayModel) : Action()
    }

}