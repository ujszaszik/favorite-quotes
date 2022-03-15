package com.newstore.favqs.features.account.signup.model

import com.newstore.favqs.features.account.signup.network.SignupResponse
import com.newstore.network.mapper.DataMappedFrom

@DataMappedFrom(SignupResponse::class)
data class SignupModel(
    val token: String
)