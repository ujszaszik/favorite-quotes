package com.newstore.favqs.features.quote.list.mapper

import com.newstore.favqs.features.quote.list.model.QuoteListModel
import com.newstore.favqs.features.quote.list.network.QuoteListResponse
import com.newstore.network.mapper.BaseMapper

class QuoteListMapper : BaseMapper<QuoteListResponse, QuoteListModel> {

    override fun map(remote: QuoteListResponse): QuoteListModel {
        val quoteMapper = QuoteItemMapper()
        return QuoteListModel(
            pageNumber = remote.page,
            isLastPage = remote.isLastPage,
            items = remote.items.map { quoteMapper.map(it) }
        )
    }

}