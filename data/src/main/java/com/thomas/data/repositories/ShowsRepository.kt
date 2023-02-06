package com.thomas.data.repositories

import androidx.lifecycle.LiveData
import com.thomas.data.common.ResponseResult
import com.thomas.data.datasource.local.Show
import com.thomas.data.models.ShowsResponse

interface ShowsRepository {

    suspend fun getShowsAndSaveToDatabase(title: String): ResponseResult<ShowsResponse>

    suspend fun getShowsFromDatabase(): LiveData<List<Show>>
}