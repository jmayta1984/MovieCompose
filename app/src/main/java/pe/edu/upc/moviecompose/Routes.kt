package pe.edu.upc.moviecompose

sealed class Routes(val route: String) {
    object Home: Routes("Home")
    object Popular: Routes("Popular")
    object Favorites: Routes("Favorites")
}