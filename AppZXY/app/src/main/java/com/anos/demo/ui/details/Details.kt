package com.anos.demo.ui.details

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
fun DetailsRoute(
    onBackClick: () -> Unit,
) {
    DetailsScreen(onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Item name",
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
                text = "Details Screen"
            )
        }
    }
}
