package com.example.artspace

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RelaxArt()
                }
            }
        }
    }
}

@Composable
fun RelaxArt(modifier: Modifier = Modifier) {

    val count = remember { mutableStateOf(1) }
    val context = LocalContext.current
    var scale by remember { mutableStateOf(1f) }

    if (count.value < 1) {
        count.value = 10
    } else if (count.value > 10) {
        count.value = 1
    }


    val imageResource = when (count.value) {
        1 -> R.drawable.image1
        2 -> R.drawable.image2
        3 -> R.drawable.image3
        4 -> R.drawable.image4
        5 -> R.drawable.image5
        6 -> R.drawable.image6
        7 -> R.drawable.image7
        8 -> R.drawable.image8
        9 -> R.drawable.image9
        else -> R.drawable.image10
    }

    val stringResource = when (count.value) {
        1 -> R.string.image1
        2 -> R.string.image2
        3 -> R.string.image3
        4 -> R.string.image4
        5 -> R.string.image5
        6 -> R.string.image6
        7 -> R.string.image7
        8 -> R.string.image8
        9 -> R.string.image9
        else -> R.string.image10
    }

    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0D5542),
                        Color(0xFF356842),
                        Color(0xFF517B41),
                        Color(0xFF6B8F3E)
                    )
                )
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)

        ) {
            Image(
                painter = painterResource(id = imageResource), contentDescription = null,
                modifier = Modifier
                    .size(300.dp, 180.dp)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
                    .pointerInput(Unit) {
                        detectTransformGestures { _, _, zoom, _ ->
                            scale = when {
                                scale < 0.5f -> 0.5f
                                scale > 3f -> 3f
                                else -> scale * zoom
                            }
                        }
                    }
                    .border(1.7.dp, Color(0xFF0F4DA8), shape = RoundedCornerShape(3))
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            Text(
                text = androidx.compose.ui.res.stringResource(id = stringResource),
                fontSize = 35.sp, fontFamily = FontFamily.Serif,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(bottom = 8.dp, start = 8.dp, top = 8.dp, end = 8.dp)
                    .shadow(1.dp, shape = RoundedCornerShape(2.dp))
            )
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF0F4DA8))
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { count.value-- },
                                onLongPress = {
                                    Toast
                                        .makeText(
                                            context,
                                            "Previous",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            )
                        }
                        .padding(
                            horizontal = 20.dp
                        )

                ) {
                    Text("Previous", fontSize = 27.sp)

                }
                Box(
                    modifier = Modifier
                        .background(Color(0xFF0F4DA8))
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { count.value++ },
                                onLongPress = {
                                    Toast
                                        .makeText(context, "Next", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            )
                        }

                        .padding(
                            horizontal = 20.dp
                        )
                ) {
                    Text("Next  ", fontSize = 27.sp)
                }

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        RelaxArt(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}