package com.newstore.favqs.preferences.settings

import com.newstore.favqs.preferences.PreferencesManager
import kotlin.properties.ReadWriteProperty

abstract class ApplicationSetting<T>(
    protected val preferencesManager: PreferencesManager,
    protected val name: String,
    protected val defaultValue: T
) : ReadWriteProperty<Any?, T>