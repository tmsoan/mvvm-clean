package com.anos.demo.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun FavoriteRoute(
    modifier: Modifier = Modifier,
) {
    FavoriteScreen()
}

@Composable
fun FavoriteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
    ) {
        Text(
            text = "Favorite Screen",
        )
    }
}