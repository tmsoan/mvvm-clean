package com.anos.demo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.anos.demo.ui.home.HomeRoute

const val LINKED_HOME_RESOURCE_ID = "linkedHomesResourceId"
const val HOME_ROUTE = "home_route/{$LINKED_HOME_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.nowinandroid.apps.samples.google.com/home/{$LINKED_HOME_RESOURCE_ID}"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onMenuClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onProfileClick: (() -> Unit)? = null,
    onItemClick: ((Int) -> Unit)? = null,
) {
    composable(
        route = HOME_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),
        arguments = listOf(
            navArgument(LINKED_HOME_RESOURCE_ID) { type = NavType.StringType },
        ),
    ) {
        HomeRoute(
            onMenuClick = onMenuClick,
            onSearchClick = onSearchClick,
            onProfileClick = onProfileClick,
            onItemClick = onItemClick,
        )
    }
}
