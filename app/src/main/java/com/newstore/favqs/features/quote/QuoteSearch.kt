package com.newstore.favqs.features.quote

import com.squareup.moshi.JsonClass

sealed interface QuoteSearchType {

    val prefix: String?
        get() = null

    val resetText: String?
        get() = null

    val needsKeywordHighlight: Boolean
        get() = false

    // needs to be class for Moshi
    @JsonClass(generateAdapter = true)
    class All : QuoteSearchType

    @JsonClass(generateAdapter = true)
    class Tag : QuoteSearchType {
        override val prefix: String = "Filtered By"
        override val resetText: String = "Reset Filter"
    }

    @JsonClass(generateAdapter = true)
    class Keyword : QuoteSearchType {
        override val prefix: String = "Searched for"
        override val resetText: String = "Reset search"
        override val needsKeywordHighlight: Boolean = true
    }
}

@JsonClass(generateAdapter = true)
class QuoteSearchParams(
    val type: QuoteSearchType,
    val keyword: String?
) {
    companion object {
        fun default(): QuoteSearchParams {
            return QuoteSearchParams(QuoteSearchType.All(), null)
        }
    }

    val isDefault: Boolean
        get() = type == QuoteSearchType.All()
}