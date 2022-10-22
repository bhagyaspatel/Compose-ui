package com.bhagyapatel.compose_ui_practice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bhagyapatel.compose_ui_practice.BottomNavigationScreen.ProfileScreen

@Composable
fun BottomNavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeGraphHolderScreen()
        }

        composable(route = BottomBarScreen.Setting.route){
            SettingsScreen()
        }

        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
    }
}