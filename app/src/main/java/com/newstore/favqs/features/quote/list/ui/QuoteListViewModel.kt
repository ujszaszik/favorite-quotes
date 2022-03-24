package com.newstore.favqs.features.quote.list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.newstore.extension.isZero
import com.newstore.favqs.data.ResourceFlow
import com.newstore.favqs.data.ResourceFlowMediator
import com.newstore.favqs.features.quote.QuoteRepository
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.QuoteSearchType.*
import com.newstore.favqs.features.quote.filterValid
import com.newstore.favqs.features.quote.list.model.QuoteListModel
import com.newstore.favqs.features.quote.list.model.QuoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteListViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {

    private var currentPageNumber = 0L
    private var currentElements = mutableListOf<QuoteModel>()
    internal var isInitialized = false

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val isRefreshing = Transformations.map(_isLoading) { isLoading ->
        isLoading && currentPageNumber.isZero()
    }

    internal fun loadMoreItems(searchParams: QuoteSearchParams) {
        val source = getSourceBySearchParams(searchParams)

        ResourceFlowMediator(
            viewModel = this,
            source = source,
            action = _action,
            loading = _isLoading,
            emitOnSuccess = {
                currentElements.addAll(it.items.filterValid())
                Action.ShowQuotesList(currentElements, it.isLastPage)
            },
            emitOnError = { Action.ShowError(it) }
        ).begin()
    }

    private fun getSourceBySearchParams(searchParams: QuoteSearchParams): ResourceFlow<QuoteListModel> {
        return when (searchParams.type) {
            is All -> repository.getQuotesList(++currentPageNumber)
            is Tag -> repository.getTagFilteredQuoteList(searchParams.keyword!!, ++currentPageNumber)
            is Keyword -> repository.getFilteredQuoteList(searchParams.keyword!!, ++currentPageNumber)
        }
    }

    internal fun resetPaging() {
        currentPageNumber = 0
        currentElements = mutableListOf()
        _action.postValue(Action.ShowQuotesList(currentElements, true))
    }

    sealed class Action {
        class ShowQuotesList(val quotes: List<QuoteModel>, val finishedLoading: Boolean) : Action()
        class ShowError(val message: String) : Action()
    }
}