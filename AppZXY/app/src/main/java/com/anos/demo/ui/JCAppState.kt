package com.anos.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.anos.demo.navigation.FAVORITE_ROUTE
import com.anos.demo.navigation.HOME_ROUTE
import com.anos.demo.navigation.SETTINGS_ROUTE
import com.anos.demo.navigation.TRENDING_ROUTE
import com.anos.demo.navigation.TopLevelDestination
import com.anos.demo.navigation.navigateToFavorite
import com.anos.demo.navigation.navigateToHome
import com.anos.demo.navigation.navigateToSearch
import com.anos.demo.navigation.navigateToSettings
import com.anos.demo.navigation.navigateToTrending
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberJCAppState(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
): JCAppState {
    return JCAppState(navController, coroutineScope)
}

@Stable
class JCAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> TopLevelDestination.HOME
            FAVORITE_ROUTE -> TopLevelDestination.FAVORITE
            TRENDING_ROUTE -> TopLevelDestination.TRENDING
            SETTINGS_ROUTE -> TopLevelDestination.SETTINGS
            else -> null
        }

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
            TopLevelDestination.FAVORITE -> navController.navigateToFavorite(topLevelNavOptions)
            TopLevelDestination.TRENDING -> navController.navigateToTrending(topLevelNavOptions)
            TopLevelDestination.SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
        }
    }

    fun navigateToSearch() = navController.navigateToSearch(
        navOptions {
            launchSingleTop = true
        }
    )
}