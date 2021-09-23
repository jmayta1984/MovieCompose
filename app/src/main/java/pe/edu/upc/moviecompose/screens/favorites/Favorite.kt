package pe.edu.upc.moviecompose.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import pe.edu.upc.moviecompose.data.model.Movie

@Composable
fun Favorite() {
    //val movies: List<Movie>
    Scaffold {
        MovieList()
    }
}


@Composable
fun MovieList(movies: List<Movie> = listOf()) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie)
        }
    }
}

@Composable
fun MovieRow(movie: Movie) {
    Column {
        Text(movie.title)
        Text(movie.overview)
    }
}
