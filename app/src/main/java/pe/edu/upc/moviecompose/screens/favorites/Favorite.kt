package pe.edu.upc.moviecompose.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import pe.edu.upc.moviecompose.data.local.AppDatabase
import pe.edu.upc.moviecompose.data.model.Movie

@Composable
fun Favorite() {
    val context = LocalContext.current
    val movies = AppDatabase.getInstance(context).MovieDao().fetchMovies()

    Scaffold {
        MovieList(movies)
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
    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(
                modifier = Modifier.weight(7f)
            ) {
                Text(movie.title)
                Text(movie.overview, maxLines = 2)
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                onClick = {
                    AppDatabase.getInstance(context).MovieDao().delete(movie)
                }) {
                Icon(Icons.Filled.Done, null)
            }
        }
    }
}