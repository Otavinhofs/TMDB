package com.example.tmdb.data.network.wrapper

data class ApiResponseWrapper<T>(
    val page: Int,
    val results: T
)
