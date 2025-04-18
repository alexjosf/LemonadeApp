package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var screenNumber by remember { mutableStateOf(1) }

    var squeezeCount by remember { mutableStateOf(0) }

    when (screenNumber) {
        1 -> {
            Screens(
                text = R.string.text_lemon_tree,
                image = R.drawable.lemon_tree,
                onImageClick = {
                    screenNumber = 2
                    squeezeCount = (2..4).random()
                }
            )
        }
        2 -> {
            Screens(
                text = R.string.text_lemon,
                image = R.drawable.lemon_squeeze,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        screenNumber = 3
                    }
                }
            )
        }
        3 -> {
            Screens(
                text = R.string.text_glass_of_lemonade,
                image = R.drawable.lemon_drink,
                onImageClick = {
                    screenNumber = 4
                }
            )
        }
        4 -> {
            Screens(
                text = R.string.text_empty_glass,
                image = R.drawable.lemon_restart,
                onImageClick = {
                    screenNumber = 1
                }
            )
        }
    }
}

@Composable
fun Screens(text: Int, image: Int, onImageClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFFC3ECD2))
                .clickable(
                    onClick = onImageClick
                )
                .padding(50.dp)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.padding(16.dp)
        )

    }
}