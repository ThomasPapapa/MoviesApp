package com.thomas.data.repositories

import com.thomas.data.common.ResponseResult
import com.thomas.data.models.ShowsResponse

interface ShowsRepository {

    suspend fun getShows(title: String): ResponseResult<ShowsResponse>

    suspend fun getShowsRemote(title: String): ResponseResult<ShowsResponse>

    suspend fun getShowsFromDB(): ResponseResult<ShowsResponse>
}