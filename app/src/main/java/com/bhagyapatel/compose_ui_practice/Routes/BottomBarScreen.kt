package com.bhagyapatel.compose_ui_practice.Routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : ImageVector,
    val badgeCount : Int = 0
){
    object Home : BottomBarScreen(
        route = "home_holder",
        title = "Home",
        icon = Icons.Default.Home,
    )
    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object Setting : BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings,
        badgeCount = 23
    )
}