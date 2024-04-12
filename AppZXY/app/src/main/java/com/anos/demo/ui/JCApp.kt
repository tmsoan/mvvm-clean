package com.anos.demo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.anos.demo.R
import com.anos.demo.navigation.JCNavHost
import com.anos.demo.navigation.TopLevelDestination

@Composable
fun JCApp(
    appState: JCAppState,
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
    ) { padding ->
        JCNavHost(
            appState = appState,
            modifier = Modifier.padding(padding)
        )
    }
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





