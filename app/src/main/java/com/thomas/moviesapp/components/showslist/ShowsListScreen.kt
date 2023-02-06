package com.thomas.moviesapp.components.showslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.thomas.moviesapp.isInternetAvailable
import com.thomas.moviesapp.viewmodels.showlist.ShowListViewModel
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowsListScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel: ShowListViewModel = remember { KoinJavaComponent.getKoin().get() }
    val shows by viewModel.shows.observeAsState(arrayListOf())
    val refreshing by viewModel.refreshing.observeAsState(false)

    LaunchedEffect(key1 = Unit) {
        if (context.isInternetAvailable()) {
            viewModel.getShows("Christmas")
        } else {
            viewModel.getShowsFromDB()
        }
    }

    val refreshScope = rememberCoroutineScope()
    fun refresh() = refreshScope.launch {
        if (context.isInternetAvailable()) {
            viewModel.getShows("Christmas")
        }
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box(
        modifier = Modifier
            .pullRefresh(state)
            .fillMaxSize()
            .background(color = Color.Black.copy(0.85f))
    ) {
        if (!shows.isNullOrEmpty()) {
            ShowsList(navController = navController, showsList = shows)
        }

        PullRefreshIndicator(
            refreshing,
            state,
            Modifier.align(Alignment.TopCenter),
            backgroundColor = Color.Black,
            contentColor = Color.Red
        )

    }

}