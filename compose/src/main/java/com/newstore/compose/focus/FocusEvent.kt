package com.newstore.compose.focus

enum class FocusEvent {
    REQUEST, CLEAR;

    fun isRequested(): Boolean = this == REQUEST

    fun isCleared(): Boolean = this == CLEAR
}