package com.anos.demo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.anos.demo.ui.state.BottomNavState
import com.anos.home.navigation.*

@Composable
fun NavGraphBottomBar(
    appState: BottomNavState,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
    ) {
        homeScreen(
            onSearchClick = appState::navigateToSearch,
            onProfileClick = appState::navigateToProfile,
            onItemClick = appState::navigateToDetailsItem,
        )
        favoriteScreen()
        trendingScreen()
        settingsScreen()
        detailsScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}