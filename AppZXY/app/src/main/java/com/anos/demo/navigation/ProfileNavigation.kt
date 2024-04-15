package com.anos.demo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.anos.demo.ui.profile.ProfileRoute

const val LINKED_PROFILE_RESOURCE_ID = "linkedProfileResourceId"
const val PROFILE_ROUTE = "profile_route/{$LINKED_PROFILE_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.nowinandroid.apps.samples.google.com/{$LINKED_PROFILE_RESOURCE_ID}"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(PROFILE_ROUTE, navOptions)
}

fun NavGraphBuilder.profileScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = PROFILE_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),
        arguments = listOf(
            navArgument(LINKED_PROFILE_RESOURCE_ID) { type = NavType.StringType },
        ),
    ) {
        ProfileRoute(
            onBackClick = onBackClick,
        )
    }
}