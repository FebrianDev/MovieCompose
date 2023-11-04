package com.febrian.moviecatalog.screen.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.febrian.moviecatalog.R
import com.febrian.moviecatalog.data.model.Movie
import com.febrian.moviecatalog.screen.navigation.Screen

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ItemMovieNowPlaying(
    navController: NavController = rememberNavController(),
    movie: Movie = Movie()
) {
    Column(modifier = Modifier
        .padding(start = 16.dp, top = 12.dp)
        .width(160.dp)
        .height(340.dp)
        .clickable {
            navController.navigate(Screen.DetailScreen.route+ "?id=${movie.id}")
        }) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
            contentDescription = "Poster Movie",
            modifier = Modifier
                .width(160.dp)
                .height(260.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

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

    }
}