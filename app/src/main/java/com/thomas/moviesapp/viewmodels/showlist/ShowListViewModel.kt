package com.thomas.moviesapp.viewmodels.showlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thomas.data.common.ResponseResult
import com.thomas.data.datasource.shows.ShowsDatasource
import com.thomas.data.models.ShowsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowListViewModel(
    private val showsDatasource: ShowsDatasource, applicationInstance: Application
) : AndroidViewModel(applicationInstance) {

    private fun getContext() = getApplication<Application>().applicationContext
    val shows = MutableLiveData<ShowsResponse>()
    val refreshing = MutableLiveData(false)

    fun getShows(title: String) {

        viewModelScope.launch(Dispatchers.IO) {

            refreshing.postValue(true)
            when (val response = showsDatasource.getShows(title)) {
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

}