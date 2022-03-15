package com.newstore.favqs.preferences

import android.annotation.SuppressLint
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(preferencesFactory: PreferencesFactory) {

    private val sharedPref = preferencesFactory.get()
    private val listeners = HashMap<String, MutableList<StoreChangeListener>>()
    private val mutedListeners = mutableListOf<StoreChangeListener>()

    fun setString(key: String, value: String, commit: Boolean = false) {
        edit({ it.putString(key, value) }, commit)
        notifyListeners(key, value)
    }

    fun setBoolean(key: String, value: Boolean, commit: Boolean = false) {
        edit({ it.putBoolean(key, value) }, commit)
        notifyListeners(key, value)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPref.getBoolean(key, default)
    }

    @SuppressLint("ApplySharedPref")
    private fun edit(action: (SharedPreferences.Editor) -> Unit, commit: Boolean = false) {
        sharedPref.edit().apply {
            action(this)
            if (commit) commit() else apply()
        }
    }

    private fun notifyListeners(key: String, value: Any?) {
        listeners[key]?.filterNot { isListenerMuted(it) }
            ?.forEach { listener ->
                listener.onValueChanged(key, value)
            }
    }

    private fun isListenerMuted(listener: StoreChangeListener): Boolean {
        return mutedListeners.contains(listener)
    }

    interface StoreChangeListener {
        fun onValueChanged(key: String, value: Any?)
    }
}