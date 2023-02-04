package com.thomas.moviesapp.components.movielist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.thomas.data.models.ShowsResponseItem

@Composable
fun ShowsList(
    navController: NavController, showsList: ArrayList<ShowsResponseItem>
) {

    LazyColumn(Modifier.fillMaxSize()) {
        items(showsList) { show ->
            ShowItem(navController, show)
        }
    }

}

@Composable
fun ShowItem(
    navController: NavController, show: ShowsResponseItem
) {
    Column() {
        Text(text = show.show.name, color = Color.White)
        Text(text = show.show.rating.average.toString(), color = Color.White)
    }
}
