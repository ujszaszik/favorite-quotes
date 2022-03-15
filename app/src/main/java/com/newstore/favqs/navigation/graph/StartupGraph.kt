package com.newstore.favqs.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.newstore.favqs.features.account.login.ui.LoginHost
import com.newstore.favqs.features.account.signup.ui.SignupHost
import com.newstore.favqs.features.main.splash.SplashHost
import com.newstore.favqs.features.quote.quoteoftheday.ui.QuoteOfTheDayHost
import com.newstore.favqs.navigation.composable

const val STARTUP_GRAPH_ROUTE = "StartupRoute"

fun NavGraphBuilder.startupGraph() {

    navigation(SplashHost.route, route = STARTUP_GRAPH_ROUTE) {

        composable(SplashHost) {
            SplashHost()
        }

        composable(QuoteOfTheDayHost) {
            QuoteOfTheDayHost()
        }

        composable(LoginHost) {
            LoginHost()
        }

        composable(SignupHost) {
            SignupHost()
        }

    }
}