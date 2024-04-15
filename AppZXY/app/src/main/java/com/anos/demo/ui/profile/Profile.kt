package com.anos.demo.ui.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileRoute(
    onBackClick: () -> Unit,
) {
    ProfileScreen(onBackClick)
}

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
) {
    Text("Profile Screen")
}
