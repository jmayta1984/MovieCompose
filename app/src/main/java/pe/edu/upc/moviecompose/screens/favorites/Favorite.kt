package pe.edu.upc.moviecompose.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import pe.edu.upc.moviecompose.data.local.AppDatabase
import pe.edu.upc.moviecompose.data.model.Movie

@Composable
fun Favorite() {
    val context = LocalContext.current
    val movies = remember { mutableStateOf(listOf<Movie>()) }
    movies.value = AppDatabase.getInstance(context).MovieDao().fetchMovies()

    Scaffold {
        MovieList(movies.value) { movie ->
            movies.value = movies.value.filter { it != movie }.toMutableList()
        }
    }
}


@Composable
fun MovieList(movies: List<Movie>, deleteMovie: (Movie) -> Unit) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie) {
                deleteMovie(it)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieRow(movie: Movie, deleteMovie: (Movie) -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(7f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    movie.title,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    movie.overview,
                    maxLines = 2,
                    style = MaterialTheme.typography.caption
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .size(32.dp),
                onClick = {
                    AppDatabase.getInstance(context).MovieDao().delete(movie)
                    deleteMovie(movie)
                }) {
                Icon(Icons.Filled.Delete, null)
            }
        }
    }
}
