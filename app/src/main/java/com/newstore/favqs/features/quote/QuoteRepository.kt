package com.newstore.favqs.features.quote

import com.newstore.favqs.data.ResourceFlow
import com.newstore.favqs.features.quote.details.model.QuoteDetailsModel
import com.newstore.favqs.features.quote.list.model.QuoteListModel
import com.newstore.favqs.features.quote.quoteoftheday.model.QuoteOfTheDayModel
import com.newstore.network.operation.networkFlow
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteService: QuoteService
) {

    fun getQuoteOfTheDay(): ResourceFlow<QuoteOfTheDayModel> {
        return networkFlow { quoteService.getQuoteOfTheDay() }
    }

    fun getQuoteById(id: Long): ResourceFlow<QuoteDetailsModel> {
        return networkFlow { quoteService.getQuoteById(id) }
    }

    fun getQuotesList(pageNumber: Long): ResourceFlow<QuoteListModel> {
        return networkFlow { quoteService.getAllQuotes(pageNumber) }
    }

    fun getFilteredQuoteList(keyword: String, pageNumber: Long): ResourceFlow<QuoteListModel> {
        return networkFlow { quoteService.getFilteredQuotes(pageNumber, keyword) }
    }

    fun getTagFilteredQuoteList(tag: String, pageNumber: Long): ResourceFlow<QuoteListModel> {
        return networkFlow { quoteService.getFilteredTagQuotes(pageNumber, tag) }
    }

    fun upVoteQuote(userToken: String, id: Long): ResourceFlow<QuoteDetailsModel> {
        return networkFlow { quoteService.upVoteQuote(userToken, id) }
    }

    fun downVoteQuote(userToken: String, id: Long): ResourceFlow<QuoteDetailsModel> {
        return networkFlow { quoteService.downVoteQuote(userToken, id) }
    }

    fun favoriteQuote(userToken: String, id: Long): ResourceFlow<QuoteDetailsModel> {
        return networkFlow { quoteService.favoriteQuote(userToken, id) }
    }

}