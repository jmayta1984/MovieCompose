package pe.edu.upc.moviecompose.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.moviecompose.data.model.Movie

class ApiResponse(

    @SerializedName("results")
    val movies: List<Movie>
)