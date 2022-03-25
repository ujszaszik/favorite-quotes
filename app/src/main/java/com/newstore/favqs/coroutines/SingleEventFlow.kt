package com.newstore.favqs.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class SingleEventFlow<T> {
    private val eventChannel = Channel<T?>()
    val eventFlow = eventChannel.receiveAsFlow()

    suspend fun emit(value: T?) {
        eventChannel.send(value)
    }

    suspend fun clear() = eventChannel.send(null)
}