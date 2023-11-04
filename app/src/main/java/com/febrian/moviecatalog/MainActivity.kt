package com.febrian.moviecatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.febrian.moviecatalog.screen.navigation.BottomNavigationBar
import com.febrian.moviecatalog.screen.navigation.Screen
import com.febrian.moviecatalog.screen.screen.*
import com.febrian.moviecatalog.ui.theme.MovieCatalogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieCatalogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.HomeScreen.route
                        ) {
                            composable(
                                route = Screen.HomeScreen.route
                            ) {
                                HomeScreen(navController = navController)
                            }

                            composable(route = Screen.SearchScreen.route) {
                                SearchScreen(navController = navController)
                            }

                            composable(route = Screen.AboutScreen.route) {
                                AboutScreen()
                            }

                            composable(
                                route = Screen.DetailScreen.route + "?id={id}",
                                arguments = listOf(navArgument("id") {
                                    type = NavType.StringType
                                    defaultValue = "Default"
                                })
                            ) {
                                DetailScreen(
                                    navController = navController,
                                    id = it.arguments?.getString("id") ?: ""
                                )
                            }
                            composable(
                                route = Screen.FavoriteScreen.route
                            ) {
                                BookmarkScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}