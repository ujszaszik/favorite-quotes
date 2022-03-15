package com.newstore.favqs.features.account.signup.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignupRequest(
    val user: SignupUserItem
)

@JsonClass(generateAdapter = true)
data class SignupUserItem(
    val login: String,
    val email: String,
    val password: String
)