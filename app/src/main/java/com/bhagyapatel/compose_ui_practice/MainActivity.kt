package com.bhagyapatel.compose_ui_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bhagyapatel.compose_ui_practice.ViewModals.MainViewModal
import com.bhagyapatel.compose_ui_practice.ui.theme.ComposeUIpracticeTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val mainViewModal : MainViewModal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUIpracticeTheme {
                BarLayouts(mainViewModal)
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun DefaultPreview() {
    ComposeUIpracticeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondaryVariant)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ExpandableCard(title = "Bhagya",
                description = "Lorem ipsum dolor sit amet. Eum quos aliquid et perspiciatis " +
                        "alias qui totam amet est alias natus et distinctio accusamus? Hic quasi " +
                        "numquam eos quia quaerat qui aspernatur dignissimos 33 voluptatem iure " +
                        "ex delectus quaerat vel galisum labore est nostrum harum. Vel debitis " +
                        "temporibus et doloremque iusto eos ratione galisum sit voluptatem dolor " +
                        "et molestias temporibus et consequatur veniam.",
                onClick = {},
                message = "Message"
            )
        }
    }
}