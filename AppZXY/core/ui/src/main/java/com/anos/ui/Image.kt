package com.anos.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NewsItemImage(
    imageUrl: String?,
    modifier: Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {
    GlideImage(
        imageModel = { imageUrl },
        modifier = modifier,
    )
}
