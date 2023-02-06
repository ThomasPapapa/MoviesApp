package com.thomas.moviesapp.components.showslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import com.thomas.data.models.ShowsResponseItem
import com.thomas.moviesapp.removeHtmlTagsAndEntities


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
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Box(
        Modifier
            .padding(10.dp)
            .fillMaxSize()
            .height((screenHeight / (1.4)).dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = {
                try {
                    navController.navigate(
                        "show_details_screen?"
                                + "showName=${show.show?.name ?: "-"}"
                                + "&showLanguage=${show.show?.language ?: "-"}"
                                + "&showGenres=${ show.show?.genres?.joinToString(", ") ?: "-"}"
                                + "&showRuntime=${show.show?.runtime ?: 0}"
                                + "&showImageOriginal=${show.show?.image?.original ?: "-"}"
                                + "&showImageMedium=${show.show?.image?.medium ?: "-"}"
                                + "&showRating=${show.show?.rating?.average ?: 0.0}"
                                + "&showSummary=${show.show?.summary ?: 0.0}"
                    )
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            })
    ) {
        GlideImage(
            imageModel = show.show?.image?.original,
            contentDescription = "show image",
            contentScale = ContentScale.FillBounds
        )

        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color.Black.copy(0.7f))
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = show.show?.name ?: "", color = Color.White, modifier = Modifier
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = (show.show?.rating?.average ?: "").toString(),
                    color = Color.White,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = (show.show?.summary ?: "").removeHtmlTagsAndEntities(),
                color = Color.White,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
    Spacer(modifier = Modifier.height(2.dp))
}
