package com.newstore.favqs.features.quote.quoteoftheday.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.newstore.compose.layout.CenteredRow
import com.newstore.compose.resources.Dimens
import com.newstore.compose.resources.Strings
import com.newstore.extension.empty
import com.newstore.favqs.features.quote.quoteoftheday.model.QuoteOfTheDayModel

@Composable
fun QuoteOfTheDayScreen(
    quote: QuoteOfTheDayModel,
    onCheckChange: (Boolean) -> Unit,
    onDismissed: () -> Unit
) {

    val isBoxChecked = remember { mutableStateOf(true) }

    Box(modifier = Modifier.padding(Dimens.paddingDefault)) {

        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { onDismissed() },
            imageVector = Icons.Default.Close,
            contentDescription = String.empty
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = Dimens.paddingDouble)
        ) {
            LargeQuoteText(
                content = quote.content!!,
                author = quote.author!!
            )
        } // Column

        CenteredRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimens.paddingDefault)
        ) {

            Checkbox(
                checked = isBoxChecked.value,
                onCheckedChange = { newValue ->
                    isBoxChecked.value = newValue
                    onCheckChange.invoke(newValue)
                }
            )

            Text(
                text = Strings.CHECKBOX_HINT,
                fontSize = Dimens.smallTextSize,
                color = Color.Black
            )
        } // Row

    } // Box

}