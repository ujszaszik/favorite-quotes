package com.newstore.favqs.features.account.profile

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.newstore.favqs.features.account.profile.ProfileViewModel.Action
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

    val isLoading = viewModel.isLoading.observeAsState().value ?: false
    val action = viewModel.action.observeAsState().value

    var username by remember { mutableStateOf<String?>(null) }

    when (action) {
        is Action.ShowProfileData -> username = action.username
        else -> Unit
    }

    ProfileDialog(
        isLoading = isLoading,
        username = username,
        onClosed = { navController.navigateUp() }
    )

}