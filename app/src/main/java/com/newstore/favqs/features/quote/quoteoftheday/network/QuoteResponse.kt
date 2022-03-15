package com.newstore.favqs.features.quote.quoteoftheday.network

import com.newstore.favqs.features.quote.quoteoftheday.mapper.QuoteOfTheDayMapper
import com.newstore.network.mapper.DataMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMapper(QuoteOfTheDayMapper::class)
data class QuoteResponse(

    @Json(name = "quote")
    val item: QuoteItem,
)

@JsonClass(generateAdapter = true)
data class QuoteItem(

    @Json(name = "id")
    val id: Long,

    @Json(name = "favorites_count")
    val favoritesCount: Long,

    @Json(name = "tags")
    val tags: List<String>,

    @Json(name = "upvotes_count")
    val upVotes: Long,

    @Json(name = "downvotes_count")
    val downVotes: Long,

    @Json(name = "author")
    val author: String?,

    @Json(name = "body")
    val content: String?
)