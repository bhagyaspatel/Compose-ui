package com.bhagyapatel.compose_ui_practice

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(id : Int?, onGoClick : (message : String) -> Unit, onAuthClick : () -> Unit) {
    var text by remember { mutableStateOf("") }

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

@Composable
@Preview(
    showSystemUi = true
)
fun ShowPreview() {
    HomeScreen(0, {}, {})
}