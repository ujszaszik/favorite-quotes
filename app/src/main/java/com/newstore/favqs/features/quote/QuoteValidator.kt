package com.newstore.favqs.features.quote

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.newstore.extension.containsAny
import com.newstore.extension.space
import com.newstore.favqs.features.quote.list.model.QuoteModel

fun List<QuoteModel>.filterValid(): List<QuoteModel> {
    return this.filter { QuoteValidator.isValid(it) }
}

object QuoteValidator {

    fun isValid(quoteModel: QuoteModel?): Boolean {
        return quoteModel?.let {
            isValidAuthor(it.author) && isValidContent(it.content)
        } ?: false
    }

    private fun isValidAuthor(author: String?): Boolean {
        return author?.let {
            areNamesStartingWithCapitalLetter(it)
        } ?: false
    }

    private fun areNamesStartingWithCapitalLetter(author: String): Boolean {
        val namesArray = author.split(String.space)
        return namesArray.all { it.firstOrNull()?.isUpperCase() ?: false }
    }

    private fun isValidContent(content: String?): Boolean {
        return content?.let {
            hasContentAtLeastFourWords(it) && !isTestContent(it)
        } ?: false
    }

    private fun hasContentAtLeastFourWords(content: String): Boolean {
        return content.split(String.space).size >= 4
    }

    private fun isTestContent(content: String): Boolean {
        return content.toLowerCase(Locale.current).containsAny("test", "quote")
    }

}