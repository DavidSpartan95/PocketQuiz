package com.davidspartan.pocketquiz.view.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidspartan.pocketquiz.ui.theme.orbitronBold
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

    Row(
        Modifier,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
        ) {
        Text(text = "Score: $score",Modifier.padding(50.dp),  fontFamily = orbitronBold, fontSize = 30.sp)
    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                        Modifier
                        ,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        pokemon!!.spriteUrl?.let { PokemonImage(imageUrl = it) }


                        Row() {
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
                        Row() {
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