package pe.edu.upc.moviecompose.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    fun fetchMovies(@Query("api_key") apiKey: String = "3cae426b920b29ed2fb1c0749f258325"): Call<ApiResponse>
}