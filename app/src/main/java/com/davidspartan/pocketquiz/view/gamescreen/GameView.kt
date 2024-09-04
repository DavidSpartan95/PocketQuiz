package com.davidspartan.pocketquiz.view.gamescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.davidspartan.pocketquiz.viewmodel.PokemonViewModel

@Composable
fun GameView(
    viewModel: PokemonViewModel,
) {
    val pokemon by viewModel.pokemon.observeAsState()

    LaunchedEffect(Unit) {
        //viewModel.fetchRandomPokemon()
    }

    Column{
        Button(onClick = {
            viewModel.fetchRandomPokemon()

        }) {
            Text("Fetch Random Pokemon")
        }

        if (pokemon?.spriteUrl.isNullOrEmpty()) {
            Text(text = "press fetch pokemon...")
        }else{
            PokemonImage(imageUrl = pokemon?.spriteUrl!!)
        }

    }
}