package com.anos.demo.ui.state

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.anos.demo.navigation.navigateToProfile
import com.anos.demo.navigation.navigateToSearch
import kotlinx.coroutines.CoroutineScope

@Stable
abstract class AppState(
    private val rootController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {
    fun navigateToSearch() = rootController.navigateToSearch(
        navOptions {
            launchSingleTop = true
        }
    )

    fun navigateToProfile() = rootController.navigateToProfile()
}