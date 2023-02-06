package com.thomas.moviesapp.viewmodels.showlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thomas.data.common.ResponseResult
import com.thomas.data.datasource.local.Show
import com.thomas.data.models.Image
import com.thomas.data.models.Rating
import com.thomas.data.models.ShowsResponse
import com.thomas.data.models.ShowsResponseItem
import com.thomas.data.repositories.ShowsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowListViewModel(
    private val showsRepository: ShowsRepository, applicationInstance: Application
) : AndroidViewModel(applicationInstance) {

    private fun getContext() = getApplication<Application>().applicationContext
    val shows = MutableLiveData<ShowsResponse>()
    val refreshing = MutableLiveData(false)

    fun getShows(title: String) {

        viewModelScope.launch(Dispatchers.IO) {

            refreshing.postValue(true)
            when (val response = showsRepository.getShowsAndSaveToDatabase(title)) {
                is ResponseResult.Success -> {
                    response.data?.let {
                        shows.postValue(it)
                        refreshing.postValue(false)
                        Log.d("ViewModel", "Success")
                    }
                }
                is ResponseResult.Error -> {
                    refreshing.postValue(false)
                    //error
                    Log.d("ViewModel", "Error")
                }
            }
        }
    }

    fun getShowsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = showsRepository.getShowsFromDatabase()
                shows.postValue(response.value?.toListOfShowsResponseItem() as ShowsResponse?)

            } catch (t: Throwable) {
                Log.d("Error db", t.message.toString())
            }
        }

    }


//    fun getShowsFromDB() {
//        viewModelScope.launch(Dispatchers.IO) {
//            showsRepository.getShowsFromDatabase().observe(Observer { shows ->
//                shows?.let {
//                    shows.postValue(it.toListOfShowsResponseItem() as ShowsResponse?)
//                }
//            })
//        }
//    }


    fun Show.mapToShowResponseItem(): ShowsResponseItem =

        ShowsResponseItem(
            show = com.thomas.data.models.Show(
                id = this.id,
                name = this.name,
                type = this.type,
                language = this.language,
                genres = this.genres,
                runtime = this.runtime,
                image = Image(this.imageMedium, this.imageOriginal),
                rating = Rating(this.ratingAverage),
                summary = this.summary
            )
        )

    private fun List<Show>.toListOfShowsResponseItem(): ArrayList<ShowsResponseItem> =
        map { it.mapToShowResponseItem() } as ArrayList<ShowsResponseItem>

//    fun mapShowsResponseItemToShowsResponse(showsResponseItem: ShowsResponseItem): ShowsResponse {
//        val showsResponse = ShowsResponse()
//        showsResponse.add(showsResponseItem)
//        return showsResponse
//    }

}