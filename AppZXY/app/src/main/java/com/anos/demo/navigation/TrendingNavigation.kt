package com.anos.demo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.anos.demo.ui.trending.TrendingRoute


const val LINKED_TRENDING_RESOURCE_ID = "linkedTrendingResourceId"
const val TRENDING_ROUTE = "trending_route/{$LINKED_TRENDING_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.nowinandroid.apps.samples.google.com/trending/{$LINKED_TRENDING_RESOURCE_ID}"

fun NavController.navigateToTrending(navOptions: NavOptions? = null) = navigate(TRENDING_ROUTE, navOptions)

fun NavGraphBuilder.trendingScreen() {
    composable(
        route = TRENDING_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),
        arguments = listOf(
            navArgument(LINKED_TRENDING_RESOURCE_ID) { type = NavType.StringType },
        ),
    ) {
        TrendingRoute()
    }
}