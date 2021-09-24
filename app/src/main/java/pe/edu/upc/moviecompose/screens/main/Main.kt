package pe.edu.upc.moviecompose.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.moviecompose.Routes
import pe.edu.upc.moviecompose.screens.favorites.Favorite
import pe.edu.upc.moviecompose.screens.home.Home
import pe.edu.upc.moviecompose.screens.popular.Popular

@Composable
fun Main() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) { Home(navController) }
        composable(Routes.Popular.route) { Popular() }
        composable(Routes.Favorites.route) { Favorite() }
    }
}