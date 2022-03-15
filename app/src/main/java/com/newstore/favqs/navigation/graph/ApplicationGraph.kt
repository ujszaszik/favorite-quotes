package com.newstore.favqs.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

val LocalNavController =
    compositionLocalOf<NavHostController> { error("LocalComposition NavController not present") }

@Composable
fun ApplicationGraph() {

    val navController = LocalNavController.current

    NavHost(navController = navController, startDestination = STARTUP_GRAPH_ROUTE) {

        startupGraph()

        quoteGraph()

    }

}