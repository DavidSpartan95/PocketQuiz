package com.davidspartan.pocketquiz.view.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.davidspartan.pocketquiz.ui.theme.orbitronBold


@Composable
fun ChoiceButton(
    text:String,
    onClick: () -> Unit
){

    Button(
        onClick = onClick,

    ) {
        Text(
            text = text,
            fontFamily = orbitronBold,
            fontSize = 20.sp
        )
    }

}