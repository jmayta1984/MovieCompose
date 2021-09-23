package pe.edu.upc.moviecompose.screens.popular

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import pe.edu.upc.moviecompose.data.model.Movie

@Composable
fun Popular() {
    val movies: List<Movie>
    Scaffold {
        MovieList(movies)
    }
}


@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie)
        }
    }
}

@Composable
fun MovieRow(movie: Movie) {
    Row {
        Column {
            Text(movie.title)
            Text(movie.overview)
        }
        Button(
            onClick = { /*TODO*/ }) {

        }
    }

}
