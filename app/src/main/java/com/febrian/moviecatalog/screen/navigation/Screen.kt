package com.febrian.moviecatalog.screen.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object DetailScreen : Screen("detail_screen")
    object FavoriteScreen : Screen("favorite_screen")
    object SearchScreen : Screen("search_screen")
    object AboutScreen : Screen("about_screen")
}