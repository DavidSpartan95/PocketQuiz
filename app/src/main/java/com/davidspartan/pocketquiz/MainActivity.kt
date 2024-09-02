package com.davidspartan.pocketquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidspartan.pocketquiz.data.SimplePokemon
import com.davidspartan.pocketquiz.data.apiCall
import com.davidspartan.pocketquiz.ui.theme.PocketQuizTheme
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocketQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var pokemon by remember { mutableStateOf<SimplePokemon?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            coroutineScope.launch {
                val randomId = Random.nextInt(0, 721)
                pokemon = apiCall(randomId)
            }
        }) {
            Text("Fetch Random Pokemon")
        }

        // Display Pokemon data if available
        pokemon?.let {
            it.spriteUrl?.let { it1 -> PokemonImage(it1) }
            Text("ID: ${it.id}, Name: ${it.name}")
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



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PocketQuizTheme {
        Greeting("Android")
    }
}