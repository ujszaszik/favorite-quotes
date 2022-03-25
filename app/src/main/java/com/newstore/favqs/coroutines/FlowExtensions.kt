package com.newstore.favqs.coroutines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


fun ViewModel.launch(block: suspend () -> Unit) {
    viewModelScope.launch { block() }
}

fun <T> mutableStateFlow() = MutableStateFlow<T?>(null)

fun <T> ViewModel.emitValue(flow: MutableStateFlow<T?>, value: T?) {
    launch { flow.emit(value) }
}

fun <T> ViewModel.emitEvent(flow: SingleEventFlow<T>, value: T?) {
    launch { flow.emit(value) }
}

@Composable
fun <T> Flow<T>.collectAsStateValue(): T? {
    return collectAsState(initial = null).value
}

fun <T> MutableStateFlow<T?>.clear() {
    this.value = null
}

fun areAllValid(vararg inputFlows: InputFlow) = inputFlows.all { it.isValid() }