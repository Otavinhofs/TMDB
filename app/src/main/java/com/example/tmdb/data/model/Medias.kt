package com.example.tmdb.data.model

data class Medias(
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
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