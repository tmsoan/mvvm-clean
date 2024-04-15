package com.anos.demo.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.anos.demo.ui.details.DetailsRoute

const val LINKED_DETAILS_RESOURCE_ID = "linkedDetailsResourceId"
const val DETAILS_ROUTE = "details_route/{$LINKED_DETAILS_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.nowinandroid.apps.samples.google.com/{$LINKED_DETAILS_RESOURCE_ID}"

fun NavController.navigateToDetails(navOptions: NavOptions? = null) {
    navigate(DETAILS_ROUTE, navOptions)
}

fun NavGraphBuilder.detailsScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = DETAILS_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),
        arguments = listOf(
            navArgument(LINKED_DETAILS_RESOURCE_ID) { type = NavType.StringType },
        ),
    ) {
        Log.d("DetailsNavigation", "detailsScreen")
        DetailsRoute(
            onBackClick = onBackClick,
        )
    }
}