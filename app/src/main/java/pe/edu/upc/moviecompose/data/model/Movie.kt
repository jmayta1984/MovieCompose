package pe.edu.upc.moviecompose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "movie_title")
    val title: String,

    @ColumnInfo(name = "movie_overview")
    val overview: String
) {

    @Ignore
    @SerializedName("poster_path")
    private var _poster: String = ""
    val poster get() = "https://image.tmdb.org/t/p/w500$_poster"

}




