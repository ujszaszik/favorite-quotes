package com.newstore.favqs.preferences.settings

import com.newstore.favqs.preferences.PreferencesManager
import kotlin.reflect.KProperty

class BooleanSetting(preferencesManager: PreferencesManager, name: String, defaultValue: Boolean) :
    ApplicationSetting<Boolean>(preferencesManager, name, defaultValue) {

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return preferencesManager.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        preferencesManager.setBoolean(name, value)
    }
}