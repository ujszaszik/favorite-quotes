package com.newstore.favqs.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newstore.network.ApiErrorCodes
import com.newstore.network.call.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

typealias ResourceFlow<T> = Flow<Resource<T>>

class ResourceFlowMediator<Action : Any, Source>(
    private val viewModel: ViewModel,
    private val source: ResourceFlow<Source>,
    private val action: MutableLiveData<Action>,
    private val loading: MutableLiveData<Boolean>,
    private val emitOnSuccess: (Source) -> Action,
    private val emitOnError: (String) -> Action
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

    private fun doOnLoading() = loading.postValue(true)

    private fun doOnSuccess(resource: Resource<Source>) {
        loading.postValue(false)
        resource.data?.let { action.postValue(emitOnSuccess.invoke(it)) }
    }

    private fun doOnError(resource: Resource<Source>) {
        loading.postValue(false)
        val errorMessage = resource.errorCode?.let {
            ApiErrorCodes.getMessageByCode(it)
        } ?: ApiErrorCodes.DEFAULT.message
        action.postValue(emitOnError.invoke(errorMessage))
    }
}