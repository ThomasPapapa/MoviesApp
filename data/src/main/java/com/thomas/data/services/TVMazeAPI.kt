package com.thomas.data.services

import com.thomas.data.models.ShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVMazeAPI {

    @GET("search/shows")
    suspend fun getShows(
        @Query("q") q: String,
    ): Response<ShowsResponse>

}