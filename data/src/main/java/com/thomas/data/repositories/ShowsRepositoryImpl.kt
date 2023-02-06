package com.thomas.data.repositories

import androidx.lifecycle.LiveData
import com.thomas.data.common.ResponseResult
import com.thomas.data.datasource.local.Show
import com.thomas.data.datasource.local.ShowDao
import com.thomas.data.datasource.remote.ShowsDatasource
import com.thomas.data.models.ShowsResponse
import kotlinx.coroutines.runBlocking

class ShowsRepositoryImpl(
    private val showsDatasource: ShowsDatasource, private val showDao: ShowDao
) : ShowsRepository {

    override suspend fun getShowsAndSaveToDatabase(title: String): ResponseResult<ShowsResponse> {
        val response = showsDatasource.getShows(title)
        if (response is ResponseResult.Success) {
            val shows = response.data?.let { mapShowsResponseToShows(it) }
            if (shows != null) {
                insertShows(shows)
            }
        }

        return response

    }

    override suspend fun getShowsFromDatabase(): LiveData<List<Show>> {
        return showDao.getAllShows()
    }

    private fun insertShows(shows: List<Show>) = runBlocking {
        showDao.insertAll(shows)
    }

    private fun mapShowsResponseToShows(showsResponse: ShowsResponse): List<Show> {
        return showsResponse.mapNotNull {
            it.show?.let { show ->
                Show(
                    id = show.id,
                    name = show.name,
                    type = show.type,
                    language = show.language,
                    genres = show.genres ?: emptyList(),
                    runtime = show.runtime ?: 0,
                    imageMedium = show.image?.medium,
                    imageOriginal = show.image?.original,
                    ratingAverage = show.rating?.average,
                    summary = show.summary
                )
            }
        }
    }


}