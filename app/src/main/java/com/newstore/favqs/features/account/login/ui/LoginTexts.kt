package com.newstore.favqs.features.account.login.ui

import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.newstore.compose.resources.Styles
import com.newstore.extension.empty

object LoginTexts {

    internal const val PAGE_TITLE = "Login"
    internal const val ERROR_DIALOG_TITLE = "Login error"

    internal const val SIGNUP_TEXT_LINK_TAG = "LinkTag::NavigateToSignUp"
    internal val signupLinkText = buildAnnotatedString {
        append("Don't have a user?  ")
        pushStringAnnotation(tag = SIGNUP_TEXT_LINK_TAG, annotation = String.empty)
        withStyle(style = Styles.linkTextStyle) { append("Sign up here") }
        append(".")
    }
}