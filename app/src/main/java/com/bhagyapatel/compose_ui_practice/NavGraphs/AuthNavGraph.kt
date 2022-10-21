package com.bhagyapatel.compose_ui_practice.NavGraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bhagyapatel.compose_ui_practice.AUTHENTICATION_ROUTE
import com.bhagyapatel.compose_ui_practice.LoginScreen
import com.bhagyapatel.compose_ui_practice.Routes
import com.bhagyapatel.compose_ui_practice.SignupScreen

fun  NavGraphBuilder.authNavGraph(
    navController : NavHostController
){
    navigation(
        startDestination = Routes.Login.route,
        route = AUTHENTICATION_ROUTE
    ){
        composable(route = Routes.Login.route){
            LoginScreen {
                navController.navigate(route = Routes.Signup.route)
            }
        }
        composable(route = Routes.Signup.route){
            SignupScreen {
                navController.popBackStack()
            }
        }
    }
}
