package com.newstore.favqs.features.main.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.runtime.compositionLocalOf

val LocalKeyboardManager =
    compositionLocalOf<KeyboardManager> { error("LocalKeyboardManager NavController not present") }


class KeyboardManager(private val activity: Activity) {

    private val inputMethodManager: InputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    fun hide() {
        val view = activity.currentFocus ?: View(activity)
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}