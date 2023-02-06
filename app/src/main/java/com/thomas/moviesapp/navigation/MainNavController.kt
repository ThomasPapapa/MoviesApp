package com.thomas.moviesapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thomas.moviesapp.components.showdetails.ShowDetailsScreen
import com.thomas.moviesapp.components.showslist.ShowsListScreen

@Composable
fun MainNavController() {

    val context = LocalContext.current
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf(Screens.SHOWS_LIST_SCREEN) }



    Scaffold(topBar = {}, bottomBar = {}) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
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
                    route = Screens.SHOW_DETAILS_SCREEN.navRoute,
                    arguments = listOf(
                        navArgument("showName") { type = NavType.StringType },
                        navArgument("showLanguage") { type = NavType.StringType },
                        navArgument("showGenres") { type = NavType.StringType },
                        navArgument("showRuntime") { type = NavType.IntType },
                        navArgument("showImageOriginal") { type = NavType.StringType },
                        navArgument("showImageMedium") { type = NavType.StringType },
                        navArgument("showRating") { type = NavType.FloatType },
                        navArgument("showSummary") { type = NavType.StringType }
                    )
                ) { bsEntry ->
                    ShowDetailsScreen(
                        bsEntry.arguments?.getString("showName"),
                        bsEntry.arguments?.getString("showLanguage"),
                        bsEntry.arguments?.getString("showGenres"),
                        bsEntry.arguments?.getInt("showRuntime"),
                        bsEntry.arguments?.getString("showImageOriginal"),
                        bsEntry.arguments?.getString("showImageMedium"),
                        bsEntry.arguments?.getFloat("showRating"),
                        bsEntry.arguments?.getString("showSummary"),

                    )
                    currentScreen = Screens.SHOW_DETAILS_SCREEN

                }
            }
        }


    }


}
