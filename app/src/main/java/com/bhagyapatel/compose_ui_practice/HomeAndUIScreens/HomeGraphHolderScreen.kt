package com.bhagyapatel.compose_ui_practice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeGraphHolderScreen(){
    lateinit var navController: NavHostController

    navController = rememberNavController()
    SetupHomeNavGraph(navController)
}