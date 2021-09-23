package pe.edu.upc.moviecompose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "movie_title")
    val title: String,

    @ColumnInfo(name = "movie_overview")
    val overview: String,

    @SerializedName("poster_path")
    val poster: String

)
