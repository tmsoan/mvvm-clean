package com.anos.demo.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anos.demo.ui.common.MyTopAppBar

@Composable
fun ProfileRoute(
    onBackClick: () -> Unit,
) {
    ProfileScreen(onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "User Profile",
                showBackArrow = true,
            ) { onBackClick() }
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Screen"
            )
        }
    }
}
