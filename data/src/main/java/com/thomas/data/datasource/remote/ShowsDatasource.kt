package com.thomas.data.datasource.remote

import com.thomas.data.common.ResponseResult
import com.thomas.data.models.ShowsResponse

interface ShowsDatasource {

    suspend fun getShows(title: String): ResponseResult<ShowsResponse>
}