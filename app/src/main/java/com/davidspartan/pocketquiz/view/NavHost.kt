package com.davidspartan.pocketquiz.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.davidspartan.pocketquiz.view.auth.LoginView
import com.davidspartan.pocketquiz.view.game.GameView
import com.davidspartan.pocketquiz.viewmodel.PokemonViewModel
import com.davidspartan.pocketquiz.viewmodel.AuthViewModel

@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "auth"){

        composable("about") {
            Column {
                Text("About Screen")
            }
        }

        navigation(
            startDestination = "login",
            route = "auth"
        ) {
            composable("login") {
                val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
                LoginView(
                    viewModel = viewModel,
                    navController = navController
                    )
            }
            composable("register") {
                val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
            }
            composable("forgot_password") {
                val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
            }
        }

        navigation(
            startDestination = "game",
            route = "main"
        ) {
            composable("game") {
                val viewModel = it.sharedViewModel<PokemonViewModel>(navController = navController)
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GameView(viewModel = viewModel)
                    Button(onClick = {
                        navController.navigate("game2")
                    }) {
                        Text(text = "Login")
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel>NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRote = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRote)
    }
    return viewModel(parentEntry)
}