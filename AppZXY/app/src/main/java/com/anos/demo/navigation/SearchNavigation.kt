package com.anos.demo.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.anos.demo.ui.search.SearchRoute

const val LINKED_SEARCH_RESOURCE_ID = "linkedSearchResourceId"
const val SEARCH_ROUTE = "search_route/{$LINKED_SEARCH_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.nowinandroid.apps.samples.google.com/{$LINKED_SEARCH_RESOURCE_ID}"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    navigate(SEARCH_ROUTE, navOptions)
}

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = SEARCH_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),
        arguments = listOf(
            navArgument(LINKED_SEARCH_RESOURCE_ID) { type = NavType.StringType },
        ),
    ) {
        SearchRoute(
            onBackClick = onBackClick,
            onSearching = {
                Log.e("SearchRoute", "Searching: $it")
            }
        )
    }
}