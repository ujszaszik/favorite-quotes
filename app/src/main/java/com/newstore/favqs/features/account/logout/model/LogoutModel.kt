package com.newstore.favqs.features.account.logout.model

import com.newstore.favqs.features.account.logout.network.LogoutResponse
import com.newstore.network.mapper.DataMappedFrom

@DataMappedFrom(LogoutResponse::class)
data class LogoutModel(val message: String)