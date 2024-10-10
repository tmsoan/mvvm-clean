package com.anos.demo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.anos.demo.navigation.NavGraphRoot
import com.anos.demo.ui.state.rememberRootNavState
import com.anos.demo.ui.theme.JCApp1Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isInitialized by remember { mutableStateOf(false) }

            val rootAppState = rememberRootNavState(
                navController = rememberNavController(),
                coroutineScope = rememberCoroutineScope()
            )
            JCApp1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    if (!isInitialized) {
                        SplashScreen {
                            isInitialized = true
                        }
                    } else {
                        NavGraphRoot(rootAppState)
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onInitializationComplete: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        onInitializationComplete()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Add your splash screen content here
        Text(
            text = "Splash Screen",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}