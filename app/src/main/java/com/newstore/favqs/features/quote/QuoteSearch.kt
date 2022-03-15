package com.newstore.favqs.features.quote

import com.squareup.moshi.JsonClass

enum class QuoteSearchType(
    val prefix: String? = null,
    val resetText: String? = null,
    val needsKeywordHighlight: Boolean = false
) {
    ALL,
    TAG("Filtered by", "Reset filter"),
    KEYWORD("Searched for", "Reset search", true)
}

@JsonClass(generateAdapter = true)
class QuoteSearchParams(
    val type: QuoteSearchType,
    val keyword: String?
) {
    companion object {
        fun default(): QuoteSearchParams {
            return QuoteSearchParams(QuoteSearchType.ALL, null)
        }
    }

    val isDefault: Boolean
        get() = type == QuoteSearchType.ALL
}