package com.thomas.data.datasource.remote

import com.thomas.data.common.ResponseResult
import com.thomas.data.models.ShowsResponse
import com.thomas.data.services.TVMazeAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowsDatasourceImpl(private val tvMazeAPI: TVMazeAPI) : ShowsDatasource {

    override suspend fun getShows(title: String): ResponseResult<ShowsResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response = tvMazeAPI.getShows(title)
                if (response.isSuccessful) {
                    return@withContext ResponseResult.Success(response.body())
                }
                else {
                    return@withContext ResponseResult.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext ResponseResult.Error(e)
            }

        }

}