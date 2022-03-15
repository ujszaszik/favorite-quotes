package com.newstore.favqs.features.account.signup.network

import com.newstore.favqs.features.account.signup.mapper.SignupMapper
import com.newstore.network.mapper.DataMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMapper(SignupMapper::class)
data class SignupResponse(
    val login: String,

    @Json(name = "User-Token")
    val userToken: String
)