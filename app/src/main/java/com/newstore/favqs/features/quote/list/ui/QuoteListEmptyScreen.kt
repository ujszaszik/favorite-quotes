package com.newstore.favqs.features.quote.list.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.newstore.compose.layout.CenteredColumn
import com.newstore.compose.resources.Dimens
import com.newstore.extension.empty
import kotlinx.coroutines.delay

private const val EMPTY_LIST_DELAY = 750L

@Composable
fun QuoteListEmptyScreen() {

    var showScreen by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = String.empty, block = {
        delay(EMPTY_LIST_DELAY)
        showScreen = true
    })

    if (showScreen) {
        CenteredColumn(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.size(Dimens.emptyListIconSize)) {
                val lottieSpec = LottieCompositionSpec.RawRes(com.newstore.favqs.R.raw.empty_box)
                val composition by rememberLottieComposition(lottieSpec)
                LottieAnimation(
                    composition = composition,
                    iterations = 1
                )
            }
        }
    }
}