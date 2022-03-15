package com.newstore.favqs.features.quote.details.network

import com.newstore.favqs.features.quote.details.mapper.QuoteDetailsMapper
import com.newstore.network.mapper.DataMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMapper(QuoteDetailsMapper::class)
data class QuoteDetailsResponse(
    val id: Long,
    val tags: List<String>,
    val author: String?,
    @Json(name = "body")
    val content: String?,
    @Json(name = "favorites_count")
    val favoritesCount: Long,
    @Json(name = "upvotes_count")
    val upVotes: Long,
    @Json(name = "downvotes_count")
    val downVotes: Long
)