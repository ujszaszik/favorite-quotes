package com.newstore.favqs.features.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.newstore.compose.keyboard.keyboardAsState
import com.newstore.compose.resources.Colors
import com.newstore.compose.resources.Dimens
import com.newstore.compose.resources.Fonts
import com.newstore.compose.resources.Icons
import com.newstore.favqs.application.QuotesApplication
import com.newstore.favqs.features.account.logout.ui.LogoutHost
import com.newstore.favqs.features.account.profile.ProfileHost
import com.newstore.favqs.features.main.util.ActionBarChannel
import com.newstore.favqs.navigation.graph.ApplicationGraph
import com.newstore.favqs.navigation.graph.LocalNavController
import com.newstore.favqs.navigation.host.*
import com.newstore.favqs.navigation.navigate

@Composable
fun MainHost() {

    val navController = LocalNavController.current

    var host by remember { mutableStateOf<Host?>(null) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        host = destination.label.toString().extractHost()
    }

    val isKeyboardOpened = keyboardAsState().value.isOpened()

    var scaffoldModifier = Modifier.statusBarsPadding()
    if (!isKeyboardOpened) scaffoldModifier = scaffoldModifier.navigationBarsWithImePadding()

    ProvideWindowInsets {
        Scaffold(
            modifier = scaffoldModifier,
            topBar = {
                if (host.showTopAppBar())
                    TopAppBar(
                        title = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = QuotesApplication.TITLE,
                                color = Colors.white,
                                fontFamily = Fonts.titleFamily,
                                textAlign = TextAlign.Center,
                                fontSize = Dimens.appBarTextSize
                            )
                        }, // Title
                        navigationIcon = {
                            if (host.isMainHost()) {
                                IconButton(onClick = { navController.navigate(ProfileHost) }) {
                                    Icons.ProfileIcon()
                                }
                            } else if (host.isSubHost()) {
                                IconButton(onClick = { navController.navigateUp() }) {
                                    Icons.BackArrowIcon()
                                }
                            }
                        }, // NavigationIcon
                        actions = {
                            IconButton(onClick = { navController.navigate(LogoutHost) }) {
                                Icons.LogoutIcon()
                            }
                            if (true == host?.type?.showOptionsItems) {
                                IconButton(onClick = { ActionBarChannel.toggleSearchView() }) {
                                    Icons.SearchIcon()
                                }
                            }
                        }, // Actions
                        backgroundColor = Colors.titleBlue
                    )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                ) {
                    ApplicationGraph()
                }
            },
        )
    }
}