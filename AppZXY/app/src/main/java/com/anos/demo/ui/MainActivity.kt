package com.anos.demo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.anos.common.event.GlobalEventManager
import com.anos.demo.navigation.NavGraphRoot
import com.anos.demo.ui.state.rememberRootNavState
import com.anos.demo.ui.theme.JCAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var globalEventManager: GlobalEventManager

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            val rootAppState = rememberRootNavState(
                navController = rememberNavController(),
                coroutineScope = rememberCoroutineScope()
            )
            JCAppTheme {
                Surface(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraphRoot(rootAppState)
                }
            }
        }

        globalEventManager.observeEvents(
            scope = lifecycleScope,
            onSessionExpired = {
                Toast.makeText(this, "SessionExpired", Toast.LENGTH_SHORT).show()
            },
            onLogout = {
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            },
            onNoNetwork = {
                Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show()
            },
            onNetworkRestored = { isConnected ->
                if (isConnected) {
                    Toast.makeText(this, "Network Restored", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Network Lost", Toast.LENGTH_SHORT).show()
                }
            },
            showToast = { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        )
    }
}
