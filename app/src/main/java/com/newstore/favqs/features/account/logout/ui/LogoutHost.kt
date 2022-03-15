package com.newstore.favqs.features.account.logout.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
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

    val action = viewModel.action.observeAsState().value

    if (action == LogoutViewModel.Action.Logout) {
        navController.navigateToLogin()
    }

    LogoutDialog(
        onLogoutApproved = { viewModel.onLogoutRequest() },
        onLogoutCancelled = { navController.navigateUp() }
    )
}