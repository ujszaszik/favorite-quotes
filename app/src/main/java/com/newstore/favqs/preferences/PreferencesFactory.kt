package com.newstore.favqs.preferences

import android.content.SharedPreferences
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesFactory @Inject constructor() {

    @Inject
    @EncryptedSharedPrefs
    lateinit var encryptedSharedPrefs: SharedPreferences

    @Inject
    @PrivateSharedPrefs
    lateinit var privateModeSharedPrefs: SharedPreferences

    fun get(): SharedPreferences {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            encryptedSharedPrefs
        } else {
            privateModeSharedPrefs
        }
    }
}