package com.davidspartan.pocketquiz.view.game.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun PokemonImage(
    imageUrl: String, modifier: Modifier = Modifier,
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = "",
        modifier = modifier.size(200.dp)
    )
}