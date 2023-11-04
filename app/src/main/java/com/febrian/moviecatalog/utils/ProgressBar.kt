package com.febrian.moviecatalog.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar() {
    CircularProgressIndicator(
        modifier = Modifier.size(100.dp),
        color = Color.Black,
        strokeWidth = 10.dp
    )
}