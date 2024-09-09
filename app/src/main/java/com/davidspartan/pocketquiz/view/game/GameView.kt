package com.davidspartan.pocketquiz.view.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.davidspartan.pocketquiz.view.game.components.ChoiceButton
import com.davidspartan.pocketquiz.view.game.components.PokemonImage
import com.davidspartan.pocketquiz.viewmodel.GameViewModel
import com.davidspartan.pocketquiz.viewmodel.PokemonViewModel


@Composable
fun GameView(
    viewModel: PokemonViewModel,
    gameViewModel: GameViewModel = remember {
        GameViewModel()
    }
) {
    val pokemon by viewModel.pokemon.observeAsState()
    val score by gameViewModel.score.observeAsState()
    val options by gameViewModel.options.observeAsState()

    LaunchedEffect(pokemon) {
        pokemon.let { notNullPokemon ->
            if (notNullPokemon != null) {
                gameViewModel.generateOptions(notNullPokemon)
            }
        }
        //viewModel.fetchRandomPokemon()
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            viewModel.fetchRandomPokemon()

        }) {
            Text("Fetch Random Pokemon")
        }

        if (pokemon?.spriteUrl.isNullOrEmpty()) {

            Text(text = "press fetch pokemon...")
        } else {
            pokemon?.let { nonNullPokemon ->
                Column(
                    //Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    nonNullPokemon.spriteUrl?.let { PokemonImage(imageUrl = it) }
                    Text(text = "Score: $score")

                    Row {
                        ChoiceButton(
                            text = options?.get(0) ?: "",
                            onClick = { gameViewModel.onAnswer(options?.get(0) ?: "", pokemon?.name!!) }
                        )

                        ChoiceButton(
                            text = options?.get(1) ?: "",
                            onClick = { gameViewModel.onAnswer(options?.get(1) ?: "", pokemon?.name!!) }
                        )
                    }
                    Row {
                        ChoiceButton(
                            text = options?.get(2) ?: "",
                            onClick = { gameViewModel.onAnswer(options?.get(2) ?: "", pokemon?.name!!) }
                        )
                        ChoiceButton(
                            text = options?.get(3) ?: "",
                            onClick = { gameViewModel.onAnswer(options?.get(3) ?: "", pokemon?.name!!) }
                        )
                    }
                }
            }
        }
    }
}