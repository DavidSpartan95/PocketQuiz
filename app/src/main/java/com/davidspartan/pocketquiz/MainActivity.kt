package com.davidspartan.pocketquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.davidspartan.pocketquiz.viewmodel.PokemonViewModel
import com.davidspartan.pocketquiz.viewmodel.SampleViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "auth"){

                composable("about"){
                    Column {
                        Text("About Screen")
                    }
                    
                }
                navigation(
                    startDestination = "login",
                    route = "auth"
                ){
                    composable("login"){
                        val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .then(Modifier),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {

                            Button(onClick = {
                                navController.navigate("main"){
                                    popUpTo("auth"){
                                        inclusive = true
                                    }
                                }
                            }) {
                                Text(text = "Login")
                            }
                        }
                    }
                    composable("register"){
                        val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)
                    }
                    composable("forgot_password"){
                        val viewModel = it.sharedViewModel<SampleViewModel>(navController = navController)
                    }
                }
                navigation(
                    startDestination = "game",
                    route = "main"
                ){
                    composable("game"){
                        val viewModel = it.sharedViewModel<PokemonViewModel>(navController = navController)
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .then(Modifier),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            GameView(
                                viewModel = viewModel
                            )
                            Button(onClick = {
                                navController.navigate("game2")
                            }) {
                                Text(text = "Login")
                            }
                        }
                        
                    }
                    composable("game2"){
                        val viewModel = it.sharedViewModel<PokemonViewModel>(navController = navController)
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .then(Modifier),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){ GameView(
                            viewModel = viewModel
                        ) }

                    }
                }
            }
        }
    }
}


@Composable
fun GameView(
    viewModel: PokemonViewModel,
    modifier: Modifier = Modifier
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

@Composable
fun PokemonImage(
    imageUrl: String ,modifier: Modifier = Modifier,
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

@Composable
inline fun <reified T : ViewModel>NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRote = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRote)
    }
    return viewModel(parentEntry)
}