package com.app.composeapplication.second_App.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreen(
    val route:String,
    val title:String,
    val icon:ImageVector,
){
    object Home : BottomNavigationScreen(
        route = "Splas_screen",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile : BottomNavigationScreen(
        route = "Profile_screen",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Settings : BottomNavigationScreen(
        route = "Login_screen",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}
