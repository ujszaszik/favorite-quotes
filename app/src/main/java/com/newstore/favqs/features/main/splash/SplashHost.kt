package com.newstore.favqs.features.main.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.favqs.features.quote.quoteoftheday.ui.QuoteOfTheDayHost
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.navigate

val SplashHost = Host(route = "SplashHost")

@Composable
fun SplashHost(viewModel: SplashViewModel = hiltViewModel()) {

    val isSplashFinished = viewModel.isSplashFinished.observeAsState().value ?: false

    if (isSplashFinished) {
        LocalNavController.current.navigate(QuoteOfTheDayHost)
    }

    SplashScreen()
}