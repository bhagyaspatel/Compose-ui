package com.bhagyapatel.compose_ui_practice

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(id : Int?, onGoClick : (message : String) -> Unit, onAuthClick : () -> Unit) {
    var text by remember { mutableStateOf("") }

    Card(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .padding(5.dp)
                    .size(80.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.mountain),
                //this below 2 lines are for scaleType(centerCrop)//See the JetpackCompose google docs
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = "Circular image"
            )

            Image(
                modifier = Modifier
                    .padding(5.dp)
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = RoundedCornerShape(10.dp) //byDefault rectangle
                    ),
                painter = painterResource(id = R.drawable.mountain),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = "Circular image"
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Home",
                color = MaterialTheme.colors.primary,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold,
            )

            Button(
                onClick = {
                    Log.d("home_screen", "HomeScreen: clicked")
                    onGoClick.invoke(text)
                },
                modifier = Modifier
                    .background(MaterialTheme.colors.onBackground)
                    .height(50.dp)
                    .width(130.dp)
            )
            {
                Text(
                    text = "Go!!",
                    color = MaterialTheme.colors.surface,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                )
            }

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.width(100.dp)
                    .padding(top = 5.dp),
                textStyle = TextStyle.Default.copy(fontSize = 18.sp)
            )

            Text(
                text = "id = $id",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 5.dp)
                    .width(150.dp)
                    .height(100.dp),
                color = MaterialTheme.colors.primary,
                fontSize = MaterialTheme.typography.h6.fontSize,
            )

            Text(
                text = "Login",
                color = MaterialTheme.colors.primary,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
                    .clickable {
                        onAuthClick.invoke()
                    }
            )
        }
    }
}

@Composable
@Preview(
    showSystemUi = true
)
fun ShowPreview() {
    HomeScreen(0, {}, {})
}