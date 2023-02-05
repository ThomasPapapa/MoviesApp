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
import com.thomas.moviesapp.navigation.Screens

fun removeHtmlTagsAndEntities(htmlText: String): String {
    return htmlText.replace("<[^>]*>".toRegex(), "").replace("&[^;]+;".toRegex(), "")
}

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
            .height((screenHeight / 2).dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = {
                navController.navigate(Screens.SHOW_DETAILS_SCREEN.navRoute)
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
                Modifier
                .padding(horizontal = 10.dp)
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
                text = removeHtmlTagsAndEntities(show.show?.summary ?: ""),
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
