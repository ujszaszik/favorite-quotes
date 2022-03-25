package com.newstore.favqs.features.quote.details.ui

import androidx.lifecycle.ViewModel
import com.newstore.favqs.coroutines.ResourceFlowMediator
import com.newstore.favqs.coroutines.emitValue
import com.newstore.favqs.coroutines.mutableStateFlow
import com.newstore.favqs.features.quote.QuoteRepository
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.QuoteSearchType
import com.newstore.favqs.features.quote.details.QuoteVoteType
import com.newstore.favqs.features.quote.details.model.QuoteDetailsModel
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuoteDetailsViewModel @Inject constructor(
    private val repository: QuoteRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _action = mutableStateFlow<Action>()
    val action: StateFlow<Action?> = _action

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    private var currentDetailsId: Long = 0

    internal fun loadQuoteDetails(id: Long) {
        currentDetailsId = id

        ResourceFlowMediator<QuoteDetailsModel>(
            source = repository.getQuoteById(id),
            viewModel = this,
            loadingFlow = _isLoading,
            onSuccess = { emitValue(_action, Action.ShowQuoteDetails(it)) },
            onError = { emitValue(_action, Action.ShowError(it)) }
        ).begin()
    }

    internal fun voteOnQuote(type: QuoteVoteType) {
        val currentUserToken = settingsManager.getCurrentUserToken()

        val source = when (type) {
            QuoteVoteType.UPVOTE -> repository.upVoteQuote(currentUserToken, currentDetailsId)
            QuoteVoteType.DOWNVOTE -> repository.downVoteQuote(currentUserToken, currentDetailsId)
            QuoteVoteType.FAVORITE -> repository.favoriteQuote(currentUserToken, currentDetailsId)
        }

        ResourceFlowMediator<QuoteDetailsModel>(
            source = source,
            viewModel = this,
            loadingFlow = _isLoading,
            onSuccess = { emitValue(_action, Action.ShowQuoteDetails(it)) },
            onError = { emitValue(_action, Action.ShowError(it)) }
        ).begin()
    }

    internal fun onTagClicked(tag: String) {
        val searchParams = QuoteSearchParams(QuoteSearchType.Tag(), tag)
        emitValue(_action, Action.StartTagSearch(searchParams))
    }

    sealed class Action {
        class ShowQuoteDetails(val quoteDetailsModel: QuoteDetailsModel) : Action()
        class ShowError(val message: String) : Action()
        class StartTagSearch(val params: QuoteSearchParams) : Action()
    }

}