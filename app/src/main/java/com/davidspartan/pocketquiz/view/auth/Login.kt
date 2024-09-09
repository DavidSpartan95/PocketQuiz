package com.davidspartan.pocketquiz.view.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.davidspartan.pocketquiz.viewmodel.AuthViewModel

@Composable
fun LoginView(
    viewModel: AuthViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate("main") {
                popUpTo("auth") {
                    inclusive = true
                }
            }
        }) {
            Text(text = "Login")
        }
    }
}