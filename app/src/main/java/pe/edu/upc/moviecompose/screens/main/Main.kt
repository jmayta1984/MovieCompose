package pe.edu.upc.moviecompose.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.moviecompose.screens.favorites.Favorite
import pe.edu.upc.moviecompose.screens.home.Home
import pe.edu.upc.moviecompose.screens.popular.Popular

@Composable
fun Main() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") { Home(navController) }
        composable("Popular") { Popular() }
        composable("Favorites") { Favorite() }
    }

}