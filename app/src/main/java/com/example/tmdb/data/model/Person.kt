package com.example.tmdb.data.model

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("known_for_department")
    val knowForDepartment: String,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("known_for")
    val knowFor: List<Media?>,
)
