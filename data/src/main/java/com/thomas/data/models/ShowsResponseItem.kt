package com.thomas.data.models


import com.google.gson.annotations.SerializedName

data class ShowsResponseItem(
    @SerializedName("show") val show: Show?
)