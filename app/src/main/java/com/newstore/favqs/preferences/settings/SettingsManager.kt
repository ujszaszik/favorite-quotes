package com.newstore.favqs.preferences.settings

import com.newstore.extension.empty
import com.newstore.favqs.preferences.PreferencesManager
import javax.inject.Inject

class SettingsManager @Inject constructor(preferencesManager: PreferencesManager) {

    companion object {
        private const val CURRENT_USER_TOKEN_KEY = "Settings::CurrentUserToken"
        private const val CURRENT_USERNAME_KEY = "Settings::CurrentUserName"
        private const val SHOW_QUOTE_ON_STARTUP_KEY = "Settings::ShowQuoteOnStartUp"
    }

    private var userToken: String
            by StringSetting(preferencesManager, CURRENT_USER_TOKEN_KEY)

    private var userName: String
            by StringSetting(preferencesManager, CURRENT_USERNAME_KEY)

    private var showQuotesOnStartup: Boolean
            by BooleanSetting(preferencesManager, SHOW_QUOTE_ON_STARTUP_KEY, true)

    fun hasUserToken(): Boolean = userToken.isNotEmpty()

    fun saveUserToken(token: String) {
        userToken = token
    }

    fun clearUserToken() {
        userToken = String.empty
    }

    fun getCurrentUserName(): String = userName

    fun saveUserName(name: String) {
        userName = name
    }

    fun setIfNeedsToShowQuotesOnStartup(newValue: Boolean) {
        if (newValue != showQuotesOnStartup) {
            showQuotesOnStartup = newValue
        }
    }

    fun needsToShowQuoteOnStartup(): Boolean = showQuotesOnStartup
}