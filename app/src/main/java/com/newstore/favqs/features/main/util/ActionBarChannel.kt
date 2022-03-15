package com.newstore.favqs.features.main.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

object ActionBarChannel : CoroutineScope {

    override val coroutineContext = Dispatchers.Main

    private val channel = Channel<ActionBarEvent?>()

    private var currentValue: ActionBarEvent? = null

    fun receive() = channel.receiveAsFlow()

    fun toggleSearchView() {
        launch {
            val newEvent = when (currentValue) {
                is ActionBarEvent.SearchRequested -> ActionBarEvent.SearchCancelled
                is ActionBarEvent.SearchCancelled, null -> ActionBarEvent.SearchRequested
            }
            currentValue = newEvent
            channel.send(currentValue)
        }
    }
}

sealed class ActionBarEvent {
    object SearchRequested : ActionBarEvent()
    object SearchCancelled : ActionBarEvent()
}