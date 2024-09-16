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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.isUnspecified
import okhttp3.internal.wait


@Composable
fun ChoiceButton(
    text: String,
    onClick: () -> Unit
) {
    val clickSound: MediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.click)
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val choiceButtonTextStyle = TextStyle(
        fontFamily = orbitronBold,
        fontSize = 20.sp
    )

    Button(
        onClick = {
            //clickSound.start().wait()
            onClick.invoke()
        },
        modifier = Modifier.size(width = (screenWidth * 0.45).dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
    ) {
        AutoResizedText(
            text = text,
            style = choiceButtonTextStyle,
            modifier = Modifier,
            color = Color.White
        )
    }

}

@Composable
fun AutoResizedText(
    text: String,
    style: TextStyle,
    modifier: Modifier,
    color: Color = style.color
) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }
    val defaultFontSize = MaterialTheme.typography.bodySmall.fontSize
    Text(
        text = text,
        color = color,
        modifier = modifier.drawWithContent {
            if (shouldDraw){
                drawContent()
            }
        },
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if(style.fontSize.isUnspecified){
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            }else{
                shouldDraw = true
            }
        }
    )
}