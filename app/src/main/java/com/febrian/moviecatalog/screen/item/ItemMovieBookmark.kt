package com.febrian.moviecatalog.screen.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.febrian.moviecatalog.R
import com.febrian.moviecatalog.data.model.MovieEntity
import com.febrian.moviecatalog.screen.navigation.Screen

@Composable
fun ItemMovieBookmark(
    navController: NavController = rememberNavController(),
    movie: MovieEntity = MovieEntity()
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(top = 12.dp, start = 16.dp, end = 16.dp)
            .clickable {
                navController.navigate(Screen.DetailScreen.route + "?id=${movie.id}")
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
            contentDescription = "Poster Movie",
            modifier = Modifier
                .weight(1.5f)
                .height(160.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(3.5f)
                .height(180.dp)
        ) {
            Text(
                text = movie.original_title.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp),
                maxLines = 2
            )

            Row(Modifier.padding(start = 8.dp, top = 8.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = "Rating Movie",
                    tint = Color("#FFC31A".toColorInt())
                )
                Text(
                    text = " ${movie.vote_average} / 10.0 TMDb",
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    color = Color("#B7B7B7".toColorInt())
                )

            }

            Row(Modifier.padding(start = 8.dp, top = 8.dp)) {
                Icon(
                    Icons.Filled.Movie,
                    contentDescription = "Popularity",
                )
                Text(
                    text = " ${movie.popularity.toString()}",
                    fontSize = 16.sp
                )

            }

            Row(Modifier.padding(start = 8.dp, top = 8.dp).fillMaxWidth()) {
                Icon(
                    Icons.Filled.CalendarMonth,
                    contentDescription = "Release Date"
                )
                Text(
                    text = " ${movie.release_date}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}