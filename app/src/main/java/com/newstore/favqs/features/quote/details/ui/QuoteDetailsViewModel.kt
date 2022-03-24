package com.newstore.favqs.features.quote.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newstore.favqs.data.ResourceFlowMediator
import com.newstore.favqs.features.quote.QuoteRepository
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.QuoteSearchType
import com.newstore.favqs.features.quote.details.QuoteVoteType
import com.newstore.favqs.features.quote.details.model.QuoteDetailsModel
import com.newstore.favqs.preferences.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteDetailsViewModel @Inject constructor(
    private val repository: QuoteRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var currentDetailsId: Long = 0

    internal fun loadQuoteDetails(id: Long) {
        currentDetailsId = id
        val source = repository.getQuoteById(id)

        ResourceFlowMediator(
            viewModel = this,
            source = source,
            action = _action,
            loading = _isLoading,
            emitOnSuccess = { Action.ShowQuoteDetails(it) },
            emitOnError = { Action.ShowError(it) }
        ).begin()
    }

    internal fun voteOnQuote(type: QuoteVoteType) {
        val currentUserToken = settingsManager.getCurrentUserToken()

        val source = when (type) {
            QuoteVoteType.UPVOTE -> repository.upVoteQuote(currentUserToken, currentDetailsId)
            QuoteVoteType.DOWNVOTE -> repository.downVoteQuote(currentUserToken, currentDetailsId)
            QuoteVoteType.FAVORITE -> repository.favoriteQuote(currentUserToken, currentDetailsId)
        }

        ResourceFlowMediator(
            viewModel = this,
            source = source,
            action = _action,
            loading = _isLoading,
            emitOnSuccess = { Action.ShowQuoteDetails(it) },
            emitOnError = { Action.ShowError(it) }
        ).begin()
    }

    internal fun onTagClicked(tag: String) {
        val searchParams = QuoteSearchParams(QuoteSearchType.TAG, tag)
        _action.postValue(Action.StartTagSearch(searchParams))
    }

    sealed class Action {
        class ShowQuoteDetails(val quoteDetailsModel: QuoteDetailsModel) : Action()
        class ShowError(val message: String) : Action()
        class StartTagSearch(val params: QuoteSearchParams) : Action()
    }


}