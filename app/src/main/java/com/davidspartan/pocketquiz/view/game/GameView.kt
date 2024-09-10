package com.davidspartan.pocketquiz.view.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.davidspartan.pocketquiz.view.game.components.ChoiceButton
import com.davidspartan.pocketquiz.view.game.components.PokemonImage
import com.davidspartan.pocketquiz.viewmodel.GameState
import com.davidspartan.pocketquiz.viewmodel.GameViewModel
import com.davidspartan.pocketquiz.viewmodel.PokemonViewModel


@Composable
fun GameView(
    viewModel: PokemonViewModel,
    gameViewModel: GameViewModel = remember {
        GameViewModel()
    }
) {
    //val pokemon by viewModel.pokemon.observeAsState()
    val pokemon by gameViewModel.pokemon.observeAsState()
    val score by gameViewModel.score.observeAsState()
    val options by gameViewModel.options.observeAsState()
    val gameState by gameViewModel.gameState.observeAsState()


    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Score: $score")
        when (gameState) {
            GameState.LOADING -> {
                CircularProgressIndicator()
                gameViewModel.startGame()
            }
            GameState.RELOAD ->{
                CircularProgressIndicator()
            }

            GameState.PLAYING -> {
                pokemon?.name?.let { pokemonName ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        pokemon!!.spriteUrl?.let { PokemonImage(imageUrl = it) }


                        Row {
                            ChoiceButton(
                                text = options?.get(0) ?: "",
                                onClick = {
                                    gameViewModel.onAnswer(
                                        options?.get(0) ?: "",
                                        pokemonName
                                    )
                                }
                            )

                            ChoiceButton(
                                text = options?.get(1) ?: "",
                                onClick = {
                                    gameViewModel.onAnswer(
                                        options?.get(1) ?: "",
                                        pokemonName
                                    )
                                }
                            )
                        }
                        Row {
                            ChoiceButton(
                                text = options?.get(2) ?: "",
                                onClick = {
                                    gameViewModel.onAnswer(
                                        options?.get(2) ?: "",
                                        pokemonName
                                    )
                                }
                            )
                            ChoiceButton(
                                text = options?.get(3) ?: "",
                                onClick = {
                                    gameViewModel.onAnswer(
                                        options?.get(3) ?: "",
                                        pokemonName
                                    )
                                }
                            )
                        }
                    }
                }

            }
            GameState.RESULT ->{
                Button(onClick = { gameViewModel.startGame() }) {
                    Text(text = "Next pokemon")
                }
            }

            null -> TODO()
        }
    }
}