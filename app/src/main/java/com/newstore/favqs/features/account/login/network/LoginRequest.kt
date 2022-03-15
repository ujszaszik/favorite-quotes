package com.newstore.favqs.features.account.login.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val user: LoginUserItem
)

@JsonClass(generateAdapter = true)
data class LoginUserItem(
    val login: String,
    val password: String
)