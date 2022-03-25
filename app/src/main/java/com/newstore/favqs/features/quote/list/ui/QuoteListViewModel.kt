package com.newstore.favqs.features.quote.list.ui

import androidx.lifecycle.ViewModel
import com.newstore.extension.isZero
import com.newstore.favqs.coroutines.ResourceFlow
import com.newstore.favqs.coroutines.ResourceFlowMediator
import com.newstore.favqs.coroutines.emitValue
import com.newstore.favqs.coroutines.mutableStateFlow
import com.newstore.favqs.features.quote.QuoteRepository
import com.newstore.favqs.features.quote.QuoteSearchParams
import com.newstore.favqs.features.quote.QuoteSearchType.*
import com.newstore.favqs.features.quote.filterValid
import com.newstore.favqs.features.quote.list.model.QuoteListModel
import com.newstore.favqs.features.quote.list.model.QuoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

@HiltViewModel
class QuoteListViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {

    private var currentPageNumber = 0L
    private var currentElements = mutableListOf<QuoteModel>()
    internal var isInitialized = false

    private val _action = mutableStateFlow<Action>()
    val action: StateFlow<Action?> = _action

    private val _isLoading = mutableStateFlow<Boolean>()
    val isLoading: StateFlow<Boolean?> = _isLoading

    val isRefreshing = _isLoading.transform<Boolean?, Boolean> { isLoading ->
        true == isLoading && currentPageNumber.isZero()
    }

    internal fun loadMoreItems(searchParams: QuoteSearchParams) {
        ResourceFlowMediator(
            source = getSourceBySearchParams(searchParams),
            viewModel = this,
            loadingFlow = _isLoading,
            onSuccess = {
                currentElements.addAll(it.items.filterValid())
                emitValue(_action, Action.ShowQuotesList(currentElements, it.isLastPage))
            },
            onError = { emitValue(_action, Action.ShowError(it)) }
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
        emitValue(_action, Action.ShowQuotesList(currentElements, true))
    }

    sealed class Action {
        class ShowQuotesList(val quotes: List<QuoteModel>, val finishedLoading: Boolean) : Action()
        class ShowError(val message: String) : Action()
    }
}