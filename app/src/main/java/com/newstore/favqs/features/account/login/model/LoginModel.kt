package com.newstore.favqs.features.account.login.model

import com.newstore.favqs.features.account.login.network.LoginResponse
import com.newstore.network.mapper.DataMappedFrom

@DataMappedFrom(LoginResponse::class)
data class LoginModel(
    val username: String,
    val token: String
)