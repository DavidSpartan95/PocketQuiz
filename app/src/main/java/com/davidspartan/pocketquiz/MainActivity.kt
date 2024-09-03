package com.davidspartan.pocketquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidspartan.pocketquiz.model.api.SimplePokemon
import com.davidspartan.pocketquiz.model.api.getPokemonService
import com.davidspartan.pocketquiz.ui.theme.PocketQuizTheme
import com.davidspartan.pocketquiz.viewmodel.PokemonViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    private val viewModel: PokemonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocketQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}


@Composable
fun Greeting(
    viewModel: PokemonViewModel,
    modifier: Modifier = Modifier
) {
    val pokemon by viewModel.pokemon.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRandomPokemon()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            viewModel.fetchRandomPokemon()
            println("ABOVE$pokemon")

        }) {
            Text("Fetch Random Pokemon")
        }

        if (pokemon?.spriteUrl.isNullOrEmpty()) {
            Text(text = "Loading...")
        }else{
            PokemonImage(imageUrl = pokemon?.spriteUrl!!)
        }

    }
}

@Composable
fun PokemonImage(
    imageUrl: String ,modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Translated description of what the image contains",
        modifier = modifier.size(200.dp)
    )
}
