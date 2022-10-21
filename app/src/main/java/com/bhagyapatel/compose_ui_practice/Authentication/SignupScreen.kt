package com.bhagyapatel.compose_ui_practice

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignupScreen(onClick : () -> Unit) {

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Signup",
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
        )

        Button(
            onClick =  {
                Log.d("home_screen", "SignupScreen : clicked")
                onClick.invoke()
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
    }
}


@Preview(showSystemUi = true)
@Composable
fun SignupPreview() {
    SignupScreen {

    }
}