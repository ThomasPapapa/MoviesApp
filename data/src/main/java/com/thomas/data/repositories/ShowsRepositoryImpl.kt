package com.thomas.data.repositories

import com.thomas.data.common.ResponseResult
import com.thomas.data.datasource.shows.ShowsDatasource
import com.thomas.data.models.ShowsResponse

class ShowsRepositoryImpl(private val showsDatasource: ShowsDatasource) : ShowsRepository {

    override suspend fun getShows(title: String): ResponseResult<ShowsResponse> {
        //if db has shows, else from remote
        if (false) {
            return getShowsFromDB()
        } else {
            return getShowsRemote(title)
        }
    }

    override suspend fun getShowsRemote(title: String): ResponseResult<ShowsResponse> {
        return showsDatasource.getShows(title)
    }

    override suspend fun getShowsFromDB(): ResponseResult<ShowsResponse> {
        return TODO()
    }


}