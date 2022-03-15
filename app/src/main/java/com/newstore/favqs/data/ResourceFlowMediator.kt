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

class ResourceFlowMediator<Action : Any, Source> private constructor() {
    private lateinit var viewModel: ViewModel
    private lateinit var source: ResourceFlow<Source>
    private lateinit var action: MutableLiveData<Action>
    private lateinit var loading: MutableLiveData<Boolean>
    private lateinit var emitOnSuccess: (Source) -> Action
    private lateinit var emitOnError: (String) -> Action

    companion object {
        fun <S : Any, T> create(): ResourceFlowMediator<S, T> = ResourceFlowMediator()
    }

    fun inViewModel(viewModel: ViewModel) = apply { this.viewModel = viewModel }

    fun onSource(source: ResourceFlow<Source>) = apply { this.source = source }

    fun toAction(action: MutableLiveData<Action>) = apply { this.action = action }

    fun connectLoadingWith(loading: MutableLiveData<Boolean>) =
        apply { this.loading = loading }

    fun emitOnSuccess(emitOnSuccess: (Source) -> Action) =
        apply { this.emitOnSuccess = emitOnSuccess }

    fun emitOnError(emitOnError: (String) -> Action) = apply { this.emitOnError = emitOnError }

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