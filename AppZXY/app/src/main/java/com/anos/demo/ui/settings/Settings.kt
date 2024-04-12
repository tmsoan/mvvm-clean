package com.anos.demo.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SettingRoute(
    modifier: Modifier = Modifier,
) {
    SettingsScreen()
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Settings Screen",
        )
    }
}
