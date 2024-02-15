package com.example.tmdb.data.model

import com.google.gson.annotations.SerializedName

data class Person(
    val name: String,
    val popularity: Double,
    @SerializedName("known_for_department")
    val knowForDepartment: String,
    @SerializedName("profile_path")
    val profilePath: String
)
