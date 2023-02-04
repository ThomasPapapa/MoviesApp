package com.thomas.moviesapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thomas.moviesapp.components.moviedetails.ShowDetailsScreen
import com.thomas.moviesapp.components.movielist.ShowsListScreen

@Composable
fun MainNavController() {

    val context = LocalContext.current
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf(Screens.SHOWS_LIST_SCREEN) }



    Scaffold(topBar = {}, bottomBar = {}) {

        Box(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = Screens.SHOWS_LIST_SCREEN.navRoute,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(
                    route = Screens.SHOWS_LIST_SCREEN.navRoute
                ) {
                    ShowsListScreen(navController = navController)
                    currentScreen = Screens.SHOWS_LIST_SCREEN

                }

                composable(
                    route = Screens.SHOW_DETAILS_SCREEN.navRoute
                ) {
                    ShowDetailsScreen(navController = navController)
                    currentScreen = Screens.SHOW_DETAILS_SCREEN

                }
            }
        }


    }


}
