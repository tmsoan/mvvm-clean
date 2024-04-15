package com.anos.demo.ui.state

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.anos.demo.ui.MAIN_ROUTE
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberRootNavState(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
): RootNavState {
    return RootNavState(navController, coroutineScope)
}

class RootNavState(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) : AppState(navController, coroutineScope) {

    fun navigateToMainTabScreen() {
        navController.navigate(MAIN_ROUTE, navOptions {
            launchSingleTop = true
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
                saveState = true
            }
        })
    }
}