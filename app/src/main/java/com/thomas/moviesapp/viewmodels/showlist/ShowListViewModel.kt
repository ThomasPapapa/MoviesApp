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

    fun getShows(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = showsDatasource.getShows(title)) {
                is ResponseResult.Success -> {
                    response.data?.let {
                        shows.postValue(it)
                        Log.d("ViewModel","Success")
                    }
                }
                is ResponseResult.Error -> {
                    //error
                    Log.d("ViewModel", "Error"  )
                }

            }
        }

    }

}