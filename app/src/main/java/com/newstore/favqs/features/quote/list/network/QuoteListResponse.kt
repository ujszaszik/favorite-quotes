package com.newstore.favqs.features.quote.list.network

import com.newstore.favqs.features.quote.list.mapper.QuoteListMapper
import com.newstore.favqs.features.quote.quoteoftheday.network.QuoteItem
import com.newstore.network.mapper.DataMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@DataMapper(QuoteListMapper::class)
class QuoteListResponse(
    val page: Long,

    @Json(name = "last_page")
    val isLastPage: Boolean,

    @Json(name = "quotes")
    val items: List<QuoteItem>
)