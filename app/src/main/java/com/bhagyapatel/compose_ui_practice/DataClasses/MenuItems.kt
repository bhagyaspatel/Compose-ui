package com.bhagyapatel.compose_ui_practice.DataClasses

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItems(
    val id : String,
    val title : String,
    val contentDescription : String,
    val icon : ImageVector
)
