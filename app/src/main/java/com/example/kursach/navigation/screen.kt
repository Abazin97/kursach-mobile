package com.example.kursach.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector) {

    data object Home : Screen("home", Icons.Default.Home)
    data object List : Screen("list", Icons.AutoMirrored.Filled.List)
    data object Profile : Screen("profile", Icons.Default.Person)
}