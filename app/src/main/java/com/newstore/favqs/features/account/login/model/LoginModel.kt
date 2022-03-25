package com.newstore.favqs.features.account.login.model

import com.newstore.favqs.features.account.login.network.LoginResponse
import com.newstore.network.mapper.DataMappedFrom

@JvmInline
@DataMappedFrom(LoginResponse::class)
value class LoginModel(val token: String)