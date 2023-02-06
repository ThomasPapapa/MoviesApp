package com.thomas.data.models


import com.google.gson.annotations.SerializedName

data class Show(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("summary")
    val summary: String?
)