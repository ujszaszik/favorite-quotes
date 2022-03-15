package com.newstore.favqs.features.quote.list.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import com.newstore.compose.color.random
import com.newstore.compose.text.getHighlightedText
import com.newstore.extension.quoted
import com.newstore.favqs.features.quote.list.network.QuoteListResponse
import com.newstore.network.mapper.DataMappedFrom

@DataMappedFrom(QuoteListResponse::class)
data class QuoteListModel(
    val pageNumber: Long,
    val isLastPage: Boolean,
    val items: List<QuoteModel>
)

data class QuoteModel(
    val id: Long,
    val tags: List<String>,
    val content: String?,
    val author: String?,
    val favoritesCount: Long,
    val upVoteCount: Long,
    val downVoteCount: Long,
    val color: Color = Color.random()
)

fun QuoteModel.getAnnotatedQuote(keyword: String?, needsHighlight: Boolean): AnnotatedString {
    var annotatedText = AnnotatedString(content!!.quoted())
    if (needsHighlight && keyword != null) {
        annotatedText = getHighlightedText(annotatedText.text, keyword)
    }
    return annotatedText
}