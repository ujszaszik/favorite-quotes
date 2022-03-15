package com.newstore.compose.text

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.newstore.extension.indexesOf

fun getHighlightedText(
    text: String,
    keyword: String,
): AnnotatedString {

    val keywordIndices = text.indexesOf(keyword)

    fun isInRange(index: Int, ranges: List<IntRange>): Boolean {
        return ranges.any { range -> index >= range.first && index <= range.last }
    }

    return buildAnnotatedString {
        text.forEachIndexed { index, char ->
            if (isInRange(index, keywordIndices)) {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(char)
                }
            } else append(char)
        }
    }
}