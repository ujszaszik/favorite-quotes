package com.newstore.favqs.features.quote.details.model

import com.newstore.favqs.features.quote.details.network.QuoteDetailsResponse
import com.newstore.network.mapper.DataMappedFrom
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMappedFrom(QuoteDetailsResponse::class)
data class QuoteDetailsModel(
    val id: Long,
    val tags: List<String>,
    val author: String?,
    val content: String?,
    val favoritesCount: Long,
    val upVotesCount: Long,
    val downVotesCount: Long
)