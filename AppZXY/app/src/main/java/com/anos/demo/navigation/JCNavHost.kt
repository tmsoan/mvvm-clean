package com.anos.demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.anos.demo.ui.JCAppState

@Composable
fun JCNavHost(
    appState: JCAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
        modifier = modifier,
    ) {
        homeScreen(
            onSearchClick = appState::navigateToSearch,
        )
        favoriteScreen()
        trendingScreen()
        settingsScreen()
        searchScreen(
            onBackClick = { navController.popBackStack() },
        )
    }
}