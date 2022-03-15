package com.newstore.favqs.features.main.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.newstore.compose.resources.Images
import com.newstore.compose.text.TitleText
import com.newstore.favqs.application.QuotesApplication

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleText(text = QuotesApplication.TITLE)

        Images.NewStoreLogo()
    }
}