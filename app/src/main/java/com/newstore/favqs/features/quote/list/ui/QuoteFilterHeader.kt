package com.newstore.favqs.features.quote.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.newstore.compose.resources.Colors
import com.newstore.compose.resources.Dimens
import com.newstore.favqs.features.quote.QuoteSearchParams

@Composable
fun QuoteFilterHeader(
    searchParams: QuoteSearchParams,
    onFilterReset: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = Dimens.paddingDefault,
                start = Dimens.paddingDouble,
                end = Dimens.paddingDouble
            )
    ) {

        Text(text = "${searchParams.type.prefix}: ${searchParams.keyword}")

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.clickable { onFilterReset() },
            text = "${searchParams.type.resetText}",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                color = Colors.blue
            )
        )
    }
}