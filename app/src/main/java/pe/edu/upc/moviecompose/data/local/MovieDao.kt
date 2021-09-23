package pe.edu.upc.moviecompose.data.local

import androidx.room.*
import pe.edu.upc.moviecompose.data.model.Movie

@Dao
interface MovieDao {

    @Query("select * from movies")
    fun fetchMovies(): List<Movie>

    @Query("select * from movies where id =:id")
    fun fetchById(id: Int): List<Movie>

    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Update
    fun update(movie: Movie)
}