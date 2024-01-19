package com.example.tmdb.data.network.wrapper

data class ApiResponseWrapper<T>(
    val page: Int,
    val results: T
)
//agora na teoria so falta popular a tela s