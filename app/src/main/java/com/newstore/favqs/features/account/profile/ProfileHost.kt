package com.newstore.favqs.features.account.profile

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.favqs.coroutines.collectAsStateValue
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.host.HostType

val ProfileHost = Host(
    route = "ProfileHost",
    type = HostType.MAIN
)

@Composable
fun ProfileHost(viewModel: ProfileViewModel = hiltViewModel()) {

    val navController = LocalNavController.current

    val isLoading = viewModel.isLoading.collectAsStateValue() ?: false
    val currentUserName = viewModel.currentUserName.collectAsStateValue()

    var username by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(currentUserName) {
        currentUserName?.let { username = it }
    }

    ProfileDialog(
        isLoading = isLoading,
        username = username,
        onClosed = { navController.navigateUp() }
    )

}