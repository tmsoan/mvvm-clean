package com.anos.demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anos.demo.ui.MAIN_ROUTE
import com.anos.demo.ui.MainScreen
import com.anos.demo.ui.state.RootNavState
import com.anos.demo.ui.state.rememberBottomNavState

@Composable
fun NavGraphRoot(
    appState: RootNavState,
) {
    val rootNavController = appState.navController
    NavHost(
        navController = rootNavController,
        startDestination = MAIN_ROUTE,
        ) {
            composable(MAIN_ROUTE) {
                MainScreen(
                    appState = rememberBottomNavState(
                        rootNavController = rootNavController,
                        navController = rememberNavController(),
                        coroutineScope = rememberCoroutineScope()
                    )
                )
            }
            searchScreen(
                onBackClick = { rootNavController.popBackStack() },
            )
            profileScreen(
                onBackClick = { rootNavController.popBackStack() }
            )
        }
}