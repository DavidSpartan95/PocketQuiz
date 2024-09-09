package com.davidspartan.pocketquiz.view.game.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ChoiceButton(
    text:String,
    onClick: () -> Unit
){
    Button(onClick = onClick) {
        Text(text)
    }
}