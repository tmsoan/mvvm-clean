package com.anos.demo.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.anos.demo.R
import com.anos.demo.navigation.NavGraphBottomBar
import com.anos.demo.navigation.TopLevelDestination
import com.anos.demo.ui.state.BottomNavState

const val MAIN_ROUTE = "main"

@Composable
fun MainScreen(
    appState: BottomNavState,
) {
    TabsScreen(appState)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabsScreen(
    appState: BottomNavState,
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val snackBarHostState = remember { SnackbarHostState() }
    val isOffline = false

    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackBarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            JCBottomBar(
                destinations = appState.topLevelDestinations,
                selectedTabIndex = selectedTabIndex,
            ) { index, destination ->
                selectedTabIndex = index
                appState.navigateToTopLevelDestination(destination)
            }
        },
        content = {
            NavGraphBottomBar(
                appState = appState,
            )
        }
    )
}

@Composable
fun JCBottomBar(
    destinations: List<TopLevelDestination>,
    selectedTabIndex: Int, onNavigateToDestination: (Int, TopLevelDestination) -> Unit
) {
    NavigationBar {
        destinations.forEachIndexed { index, destination ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector =
                        if (selectedTabIndex == index)
                            destination.selectedIcon
                        else destination.unselectedIcon,
                        contentDescription = destination.title
                    )
                },
                label = {
                    Text(destination.title)
                },
                selected = selectedTabIndex == index,
                onClick = {
                    onNavigateToDestination(index, destination)
                }
            )
        }
    }
}
