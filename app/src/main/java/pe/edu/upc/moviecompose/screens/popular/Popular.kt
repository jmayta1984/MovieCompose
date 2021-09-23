package pe.edu.upc.moviecompose.screens.popular

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import pe.edu.upc.moviecompose.data.local.AppDatabase
import pe.edu.upc.moviecompose.data.model.Movie
import pe.edu.upc.moviecompose.data.remote.ApiClient
import pe.edu.upc.moviecompose.data.remote.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Popular() {
    val movies = remember { mutableStateOf(listOf<Movie>()) }
    val apiService = ApiClient.build()
    val fetchMovies = apiService.fetchMovies("3cae426b920b29ed2fb1c0749f258325")

    fetchMovies.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            Log.d("Popular", response.body().toString())

            if (response.isSuccessful) {
                movies.value = response.body()!!.movies
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            Log.d("Popular", t.toString())
        }

    })

    Scaffold {
        MovieList(movies.value)
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieRow(movie: Movie) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.poster}"),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
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
                    AppDatabase.getInstance(context).MovieDao().insert(movie)
                }) {
                Icon(Icons.Filled.Done, null)
            }
        }
    }
}
