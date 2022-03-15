package com.newstore.compose.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.newstore.compose.R
import com.newstore.compose.layout.DoubleSpacer
import com.newstore.compose.layout.LargeSpacer

@Composable
fun GreetingText(username: String?) {
    Column(modifier = Modifier.fillMaxWidth()) {

        if (username != null) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = getAnnotatedGreeting(username),
                textAlign = TextAlign.Center
            )

            DoubleSpacer()

        } else {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val lottieSpec = LottieCompositionSpec.RawRes(R.raw.progress_bar)
                val composition by rememberLottieComposition(lottieSpec)
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )

                LargeSpacer()

            } // Column

        } // else

    } // Column
}

private fun getAnnotatedGreeting(username: String): AnnotatedString {
    return buildAnnotatedString {
        append("Hello, ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(username)
        }
        append("!")
    }
}