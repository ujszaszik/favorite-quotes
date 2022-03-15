package com.newstore.favqs.features.quote.list.mapper

import com.newstore.favqs.features.quote.list.model.QuoteModel
import com.newstore.favqs.features.quote.quoteoftheday.network.QuoteItem
import com.newstore.network.mapper.BaseMapper

class QuoteItemMapper : BaseMapper<QuoteItem, QuoteModel> {

    override fun map(remote: QuoteItem): QuoteModel {
        return QuoteModel(
            id = remote.id,
            tags = remote.tags,
            content = remote.content,
            author = remote.author,
            favoritesCount = remote.favoritesCount,
            upVoteCount = remote.upVotes,
            downVoteCount = remote.downVotes
        )
    }
}