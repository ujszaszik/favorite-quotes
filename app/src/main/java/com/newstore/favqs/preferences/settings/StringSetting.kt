package com.newstore.favqs.preferences.settings

import com.newstore.extension.empty
import com.newstore.favqs.preferences.PreferencesManager
import kotlin.reflect.KProperty

class StringSetting(
    preferencesManager: PreferencesManager,
    name: String,
    defaultValue: String = String.empty
) : ApplicationSetting<String>(preferencesManager, name, defaultValue) {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return preferencesManager.getString(name) ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        preferencesManager.setString(name, value)
    }
}