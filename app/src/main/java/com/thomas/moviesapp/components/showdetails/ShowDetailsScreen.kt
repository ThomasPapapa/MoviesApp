package com.thomas.moviesapp.components.showdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.thomas.moviesapp.removeHtmlTagsAndEntities

@Composable
fun ShowDetailsScreen(
    showName: String?,
    showLanguage: String?,
    showGenres: String?,
    showRuntime: Int?,
    showImageOriginal: String?,
    showImageMedium: String?,
    showRating: Float?,
    showSummary: String?
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val imageSource =
        if (!showImageOriginal.isNullOrBlank() && showImageOriginal != "-") showImageOriginal else showImageMedium

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(0.85f))
            .verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            imageModel = imageSource,
            contentDescription = "show image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.height((screenHeight / (1.5)).dp)
        )
        Column(Modifier.padding(8.dp)) {
            Text(text = showName ?: "", color = Color.White)
            Text(
                text = if (showRuntime == 0) "" else showRuntime.toString() + " minutes",
                color = Color.White
            )
            Text(text = showLanguage ?: "", color = Color.White)
            Text(text = showGenres ?: "", color = Color.White)
            Text(
                text = if (showRating == 0.0f) "" else showRating.toString() + "/10",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = showSummary?.removeHtmlTagsAndEntities() ?: "", color = Color.White)
        }
    }


}