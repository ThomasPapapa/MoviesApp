package com.thomas.moviesapp.components.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.thomas.moviesapp.viewmodels.showlist.ShowListViewModel
import org.koin.java.KoinJavaComponent

@Composable
fun ShowsListScreen(navController: NavController) {

    val viewModel: ShowListViewModel = remember { KoinJavaComponent.getKoin().get() }
    val shows by viewModel.shows.observeAsState(arrayListOf())

    LaunchedEffect(key1 = Unit) {
        viewModel.getShows("Friends")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(0.85f))
    ) {
        if (shows.isNotEmpty()) {
            ShowsList(navController = navController, showsList = shows)
        }

    }

}