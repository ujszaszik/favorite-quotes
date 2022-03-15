package com.newstore.favqs.features.quote.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newstore.favqs.data.ResourceFlowMediator
import com.newstore.favqs.features.quote.QuoteRepository
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.QuoteSearchType
import com.newstore.favqs.features.quote.details.model.QuoteDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteDetailsViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    internal fun loadQuoteDetails(id: Long) {
        val source = repository.getQuoteById(id)

        ResourceFlowMediator.create<Action, QuoteDetailsModel>()
            .inViewModel(this)
            .onSource(source)
            .toAction(_action)
            .connectLoadingWith(_isLoading)
            .emitOnSuccess { Action.ShowQuoteDetails(it) }
            .emitOnError { Action.ShowError(it) }
            .begin()
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