package com.davidspartan.pocketquiz.view.game.components

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidspartan.pocketquiz.R
import com.davidspartan.pocketquiz.ui.theme.orbitronBold
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import okhttp3.internal.wait


@Composable
fun ChoiceButton(
    text:String,
    onClick: () -> Unit
){
    val clickSound: MediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.click)
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp

    Button(
        onClick = {
            //clickSound.start().wait()

            onClick.invoke()
                  },
        modifier = Modifier.size(width = (screenWidth*0.45).dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
    ) {
        Text(
            text = text,
            fontFamily = orbitronBold,
            fontSize = 20.sp
        )
    }

}