package com.example.tmdb.ui.viewmodel

import com.example.tmdb.data.model.Medias
import com.example.tmdb.data.network.Api
import com.example.tmdb.data.network.getApi

class MediasRepository {

    private val mediaApi = getApi(Api::class.java)
    private val bearerToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmNkYTEyZWQ2M2U1YjFiZ" +
            "WM3NzFmNjJhOTE2ZDdkYyIsInN1YiI6IjY1OTM5MjcyY2U0ZGRjNmNkMzdmMjEzNCIsInNjb3BlcyI6W" +
            "yJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.wW5P6uNmVn7il9cW9wGzuINdeZte1dkeZ_4LE4maBBA"

    suspend fun getPopularMovies(): List<Medias> {
        val response = mediaApi.getMoviesList(header = bearerToken)
        return if (response.isSuccessful)
            response.body()?.results.orEmpty()
        else listOf()
    }

    suspend fun getPopularSeries(): List<Medias> {
        val response = mediaApi.getSeriesList(header = bearerToken)
        return if (response.isSuccessful)
            response.body()?.results.orEmpty()
        else listOf()
    }


}
