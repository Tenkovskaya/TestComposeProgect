package com.tenkovskaya.testcomposeprogect.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.tenkovskaya.testcomposeprogect.R

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.back),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}