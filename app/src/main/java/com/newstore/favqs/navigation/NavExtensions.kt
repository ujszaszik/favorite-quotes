package com.newstore.favqs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.newstore.favqs.data.PolymorphicMoshiBuilder
import com.newstore.favqs.navigation.host.Host
import com.newstore.favqs.navigation.host.compress
import com.newstore.favqs.navigation.host.extractHost

internal fun NavGraphBuilder.composable(
    host: Host,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
            this.label = host.compress()
            this.route = host.route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

internal fun NavGraphBuilder.dialog(
    host: Host,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    dialogProperties: DialogProperties = DialogProperties(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        DialogNavigator.Destination(
            provider[DialogNavigator::class],
            dialogProperties,
            content
        ).apply {
            this.label = host.compress()
            this.route = host.route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

internal fun NavController.navigate(
    host: Host,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    navigate(host.route, navOptions, navigatorExtras)
}

inline fun <reified T> NavController.navigate(host: Host, param: T?) {
    navigate(
        host.route.plus(
            PolymorphicMoshiBuilder.build().adapter(T::class.java).toJson(param)
        )
    )
}

fun NavDestination.asHost(): Host? {
    return label.toString().extractHost()
}