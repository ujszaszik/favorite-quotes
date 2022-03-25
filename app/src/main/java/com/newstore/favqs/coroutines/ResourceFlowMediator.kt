package com.newstore.favqs.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newstore.network.ApiErrorCodes
import com.newstore.network.call.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

typealias ResourceFlow<T> = Flow<Resource<T>>

class ResourceFlowMediator<Source>(
    private val source: ResourceFlow<Source>,
    private val viewModel: ViewModel,
    private val loadingFlow: MutableStateFlow<Boolean?>,
    private val onSuccess: (Source) -> Unit,
    private val onError: (String) -> Unit
) {

    fun begin() {
        viewModel.viewModelScope.launch {
            source.collect { resource ->
                when (resource.status) {
                    Resource.Status.LOADING -> doOnLoading()
                    Resource.Status.SUCCESS -> doOnSuccess(resource)
                    Resource.Status.ERROR -> doOnError(resource)
                }
            }
        }
    }

    private fun doOnLoading() = viewModel.launch { loadingFlow.emit(true) }

    private fun doOnSuccess(resource: Resource<Source>) {
        viewModel.launch {
            loadingFlow.emit(false)
            resource.data?.let { onSuccess.invoke(it) }
        }
    }

    private fun doOnError(resource: Resource<Source>) {
        viewModel.launch {
            loadingFlow.emit(false)
            val errorMessage = resource.errorCode?.let {
                ApiErrorCodes.getMessageByCode(it)
            } ?: ApiErrorCodes.DEFAULT.message
            onError.invoke(errorMessage)
        }
    }
}