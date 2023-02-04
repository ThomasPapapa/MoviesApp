package com.thomas.moviesapp.components.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thomas.moviesapp.viewmodels.showlist.ShowListViewModel
import org.koin.java.KoinJavaComponent

@Composable
fun ShowsListScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: ShowListViewModel = remember { KoinJavaComponent.getKoin().get() }
    val shows by viewModel.shows.observeAsState(arrayListOf())

    LaunchedEffect(key1 = Unit) {
        viewModel.getShows("Friends")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Text(text = "Shows:", color = Color.White)
        Spacer(modifier = Modifier.height(10.dp))
        if (shows.isNotEmpty()) {
            ShowsList(navController = navController, showsList = shows)
        }

    }

}