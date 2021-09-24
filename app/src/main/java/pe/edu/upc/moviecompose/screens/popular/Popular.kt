package pe.edu.upc.moviecompose.screens.popular

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
    val fetchMovies = apiService.fetchMovies()

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
    val favorite = remember { mutableStateOf(false) }
    favorite.value = (AppDatabase.getInstance(context).MovieDao().fetchById(movie.id).isNotEmpty())
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(movie.poster),
                contentDescription = null,
                modifier = Modifier
                    .size(92.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
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
                    if (favorite.value)
                        AppDatabase.getInstance(context).MovieDao().delete(movie)
                    else
                        AppDatabase.getInstance(context).MovieDao().insert(movie)
                    favorite.value = !favorite.value
                }) {

                Icon(
                    Icons.Filled.Favorite,
                    "",
                    tint = if (favorite.value) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

