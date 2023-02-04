package com.thomas.data.datasource.shows

import com.thomas.data.common.ResponseResult
import com.thomas.data.models.ShowsResponse

interface ShowsDatasource {

    suspend fun getShows(title: String): ResponseResult<ShowsResponse>
}