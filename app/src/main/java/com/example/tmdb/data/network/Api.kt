package com.example.tmdb.data.network

import com.example.tmdb.data.model.Medias
import com.example.tmdb.data.model.Person
import com.example.tmdb.data.network.wrapper.ApiResponseWrapper
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.util.concurrent.TimeUnit

//  Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmNkYTEyZWQ2M2U1YjFiZWM3NzFmNjJhOTE2ZDdkYyIsInN1YiI6IjY1OTM5MjcyY2U0ZGRjNmNkMzdmMjEzNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.wW5P6uNmVn7il9cW9wGzuINdeZte1dkeZ_4LE4maBBA

interface Api {

    @GET("movie/day?language=pt-BR")
    suspend fun getMoviesList(
        @Header("Authorization") header: String
    ) : Response<ApiResponseWrapper<List<Medias>>>

    @GET("tv/day?language=pt-BR")
    suspend fun getSeriesList(
        @Header("Authorization") header: String
    ) : Response<ApiResponseWrapper<List<Medias>>>
    @GET("person/day?language=pt-BR")
    suspend fun getPersonList(
        @Header("Authorization") header: String
    ) : Response<ApiResponseWrapper<List<Person>>>
}

private const val TIMEOUT: Long = 60
fun <T> getApi(serviceClass: Class<T>): T {

    val client = OkHttpClient.Builder()
    client.readTimeout(TIMEOUT, TimeUnit.SECONDS)
    client.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

    val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/trending/")
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create(gson))

    return retrofit.build().create(serviceClass)
}
