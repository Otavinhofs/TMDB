package com.example.tmdb.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Media(
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: Date?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("first_air_date")
    val firstAirDate: Date?,
    @SerializedName("name")
    val name: String?,
)
