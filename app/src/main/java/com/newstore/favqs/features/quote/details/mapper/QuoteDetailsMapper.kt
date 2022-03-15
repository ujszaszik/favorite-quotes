package com.newstore.favqs.features.quote.details.mapper

import com.newstore.favqs.features.quote.details.model.QuoteDetailsModel
import com.newstore.favqs.features.quote.details.network.QuoteDetailsResponse
import com.newstore.network.mapper.BaseMapper

class QuoteDetailsMapper : BaseMapper<QuoteDetailsResponse, QuoteDetailsModel> {

    override fun map(remote: QuoteDetailsResponse): QuoteDetailsModel {
        return QuoteDetailsModel(
            id = remote.id,
            tags = remote.tags,
            author = remote.author,
            content = remote.content,
            favoritesCount = remote.favoritesCount,
            upVotesCount = remote.upVotes,
            downVotesCount = remote.downVotes
        )
    }
}