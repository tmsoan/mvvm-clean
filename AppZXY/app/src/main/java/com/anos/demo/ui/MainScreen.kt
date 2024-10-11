package com.anos.demo.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.anos.demo.R
import com.anos.demo.navigation.NavGraphBottomBar
import com.anos.demo.navigation.TopLevelDestination
import com.anos.demo.ui.state.BottomNavState
import kotlinx.coroutines.delay

const val MAIN_ROUTE = "main"

sealed class BackPress {
    data object Idle : BackPress()
    data object InitialTouch : BackPress()
}

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
    val context = LocalContext.current
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var showToastBackPress by remember { mutableStateOf(false) }

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

    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }

    if (showToastBackPress) {
        Toast.makeText(context, "Press again to EXIT", Toast.LENGTH_SHORT).show()
        showToastBackPress = false
    }

    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToastBackPress = true
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
