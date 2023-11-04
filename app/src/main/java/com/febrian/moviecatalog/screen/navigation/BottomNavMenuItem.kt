package com.febrian.moviecatalog.screen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavMenuItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavMenuItem(
        route = Screen.HomeScreen.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object Search : BottomNavMenuItem(
        route = Screen.SearchScreen.route,
        title = "Search",
        icon = Icons.Default.Search
    )

    object About : BottomNavMenuItem(
        route = Screen.AboutScreen.route,
        title = "About",
        icon = Icons.Default.Person
    )


}