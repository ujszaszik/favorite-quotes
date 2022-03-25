package com.newstore.favqs.features.account.logout.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.favqs.coroutines.collectAsStateValue
import com.newstore.favqs.features.account.login.ui.navigateToLogin
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.host.HostType

val LogoutHost = Host(
    route = "LogoutHost",
    type = HostType.MAIN
)

@Composable
fun LogoutHost(viewModel: LogoutViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    val isLogoutSuccessful = viewModel.isLogoutSuccessful.collectAsStateValue()

    LaunchedEffect(isLogoutSuccessful) {
        if (true == isLogoutSuccessful) navController.navigateToLogin()
    }

    LogoutDialog(
        onLogoutApproved = { viewModel.onLogoutRequest() },
        onLogoutCancelled = { navController.navigateUp() }
    )
}