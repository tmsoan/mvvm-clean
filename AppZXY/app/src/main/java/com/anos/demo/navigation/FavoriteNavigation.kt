package com.anos.demo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.anos.demo.ui.favorite.FavoriteRoute


const val LINKED_FAVORITE_RESOURCE_ID = "linkedFavoriteResourceId"
const val FAVORITE_ROUTE = "favorite_route/{$LINKED_FAVORITE_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.nowinandroid.apps.samples.google.com/favorite/{$LINKED_FAVORITE_RESOURCE_ID}"

fun NavController.navigateToFavorite(navOptions: NavOptions? = null) = navigate(FAVORITE_ROUTE, navOptions)

fun NavGraphBuilder.favoriteScreen() {
    composable(
        route = FAVORITE_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),
        arguments = listOf(
            navArgument(LINKED_FAVORITE_RESOURCE_ID) { type = NavType.StringType },
        ),
    ) {
        FavoriteRoute()
    }
}