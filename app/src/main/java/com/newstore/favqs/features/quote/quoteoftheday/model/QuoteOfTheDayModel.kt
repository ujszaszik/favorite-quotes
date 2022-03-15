package com.newstore.favqs.features.quote.quoteoftheday.model

import android.os.Parcelable
import com.newstore.favqs.features.quote.quoteoftheday.network.QuoteResponse
import com.newstore.network.mapper.DataMappedFrom
import kotlinx.parcelize.Parcelize

@DataMappedFrom(QuoteResponse::class)
@Parcelize
class QuoteOfTheDayModel(
    val content: String?,
    val author: String?
) : Parcelable