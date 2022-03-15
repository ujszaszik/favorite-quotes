package com.newstore.favqs.features.account.logout.network

import com.newstore.favqs.features.account.logout.mapper.LogoutMapper
import com.newstore.network.mapper.DataMapper
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMapper(LogoutMapper::class)
data class LogoutResponse(
    val message: String
)