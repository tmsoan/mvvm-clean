package com.anos.demo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.anos.demo.ui.settings.SettingRoute

const val LINKED_SETTINGS_RESOURCE_ID = "linkedSettingsResourceId"
const val SETTINGS_ROUTE = "settings_route/{$LINKED_SETTINGS_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.nowinandroid.apps.samples.google.com/settings/{$LINKED_SETTINGS_RESOURCE_ID}"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) = navigate(SETTINGS_ROUTE, navOptions)

fun NavGraphBuilder.settingsScreen() {
    composable(
        route = SETTINGS_ROUTE,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),
        arguments = listOf(
            navArgument(LINKED_SETTINGS_RESOURCE_ID) { type = NavType.StringType },
        ),
    ) {
        SettingRoute()
    }
}