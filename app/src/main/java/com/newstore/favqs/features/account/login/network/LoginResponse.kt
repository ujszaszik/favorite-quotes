package com.newstore.favqs.features.account.login.network

import com.newstore.favqs.features.account.login.mapper.LoginMapper
import com.newstore.network.mapper.DataMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMapper(LoginMapper::class)
data class LoginResponse(
    val login: String,
    val email: String,

    @Json(name = "User-Token")
    val userToken: String
)