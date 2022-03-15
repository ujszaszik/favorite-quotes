package com.newstore.compose.keyboard

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalView

//  Copyright © 2021. OTP Mobil Szolgáltató Kft. All rights reserved.

enum class Keyboard {
    Opened, Closed;

    fun isOpened() = this == Opened

    fun isClosed() = this == Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}