package com.newstore.favqs.features.quote.quoteoftheday.mapper

import com.newstore.favqs.features.quote.quoteoftheday.model.QuoteOfTheDayModel
import com.newstore.favqs.features.quote.quoteoftheday.network.QuoteResponse
import com.newstore.network.mapper.BaseMapper

class QuoteOfTheDayMapper : BaseMapper<QuoteResponse, QuoteOfTheDayModel> {

    override fun map(remote: QuoteResponse): QuoteOfTheDayModel {
        return QuoteOfTheDayModel(
            content = remote.item.content,
            author = remote.item.author
        )
    }
}