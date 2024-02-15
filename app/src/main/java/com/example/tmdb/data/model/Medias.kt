package com.example.tmdb.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Medias(
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    val release_date: Date?,
    val title: String?,
    val vote_average: Double,
    val vote_count: Int,
    val media_type: String,
    val first_air_date: Date?,
    val name: String? ,
)
