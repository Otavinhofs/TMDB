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

data class X(
    val page: Int,
    val results: List<MyXObject>
) {
    data class MyXObject(val x: String)
}


data class Y(
    val page: Int,
    val results: List<MyYObject>
) {
    data class MyYObject(val y: String)
}


// dai tem que botar page e results em todos saquei ai fica uma bosta
// mais bosta do que eu fiz ontem na pizzaria aham mas pode crer entendi eu posso usar os estados ja ne?
// Sim, tu pode ir criando cofnorme precisa la no viewmodel