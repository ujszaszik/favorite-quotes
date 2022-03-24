package com.newstore.favqs.features.quote

import com.newstore.favqs.data.TOKEN_HEADER
import com.newstore.favqs.features.quote.details.model.QuoteDetailsModel
import com.newstore.favqs.features.quote.list.model.QuoteListModel
import com.newstore.favqs.features.quote.quoteoftheday.model.QuoteOfTheDayModel
import com.newstore.network.call.Resource
import retrofit2.http.*

interface QuoteService {

    @GET("qotd")
    suspend fun getQuoteOfTheDay(): Resource<QuoteOfTheDayModel>

    @Headers(TOKEN_HEADER)
    @GET("quotes/{id}")
    suspend fun getQuoteById(@Path("id") id: Long): Resource<QuoteDetailsModel>

    @Headers(TOKEN_HEADER)
    @GET("quotes")
    suspend fun getAllQuotes(@Query(value = "page") pageNumber: Long): Resource<QuoteListModel>

    @Headers(TOKEN_HEADER)
    @GET("quotes")
    suspend fun getFilteredQuotes(
        @Query(value = "page") pageNumber: Long,
        @Query(value = "filter") keyword: String
    ): Resource<QuoteListModel>

    @Headers(TOKEN_HEADER)
    @GET("quotes")
    suspend fun getFilteredTagQuotes(
        @Query(value = "page") pageNumber: Long,
        @Query(value = "filter") tag: String,
        @Query(value = "type") type: String = "tag"
    ): Resource<QuoteListModel>

    @Headers(TOKEN_HEADER)
    @PUT("quotes/{id}/upvote")
    suspend fun upVoteQuote(
        @Header("User-Token") userToken: String,
        @Path("id") id: Long
    ): Resource<QuoteDetailsModel>

    @Headers(TOKEN_HEADER)
    @PUT("quotes/{id}/downvote")
    suspend fun downVoteQuote(
        @Header("User-Token") userToken: String,
        @Path("id") id: Long
    ): Resource<QuoteDetailsModel>

    @Headers(TOKEN_HEADER)
    @PUT("quotes/{id}/fav")
    suspend fun favoriteQuote(
        @Header("User-Token") userToken: String,
        @Path("id") id: Long
    ): Resource<QuoteDetailsModel>

}