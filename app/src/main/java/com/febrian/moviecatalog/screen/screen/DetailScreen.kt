package com.febrian.moviecatalog.screen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.febrian.moviecatalog.R
import com.febrian.moviecatalog.data.model.Movie
import com.febrian.moviecatalog.screen.vm.BookmarkViewModel
import com.febrian.moviecatalog.screen.vm.MovieViewModel
import com.febrian.moviecatalog.utils.Helper
import com.febrian.moviecatalog.utils.ProgressBar
import com.febrian.moviecatalog.utils.Result

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailScreen(
    movieViewModel: MovieViewModel = hiltViewModel(),
    bookmarkViewModel: BookmarkViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
    helper: Helper = Helper(LocalContext.current),
    id: String = ""
) {

    movieViewModel.getDetailMovie(id)

    val state = movieViewModel.detailState.collectAsState()
    val loading = movieViewModel.loading.collectAsState()

    var icon by remember { mutableStateOf(Icons.Filled.BookmarkBorder) }

    when (state.value) {
        is Result.Success -> {
            val movie = (state.value as Result.Success<Movie>).data
            icon =
                if (bookmarkViewModel.moviesExist(movie.id!!)) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder
            Box(modifier = Modifier.fillMaxSize()) {

                Image(
                    painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"),
                    contentDescription = "Poster Movie",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    contentScale = ContentScale.FillWidth
                )

                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Arrow Back",
                    modifier = Modifier
                        .padding(top = 24.dp, start = 16.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    tint = Color.White
                )

                Card(
                    modifier = Modifier
                        .padding(top = 240.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {

                        if (loading.value) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                ProgressBar()
                            }
                        }

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = movie.original_title.toString(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(9f)
                            )

                            Icon(
                                icon,
                                contentDescription = "Bookmark",
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .weight(1f)
                                    .clickable {
                                        if (bookmarkViewModel.moviesExist(movie.id!!)) {
                                            icon = Icons.Filled.BookmarkBorder
                                            helper.showToast("Success Remove from Bookmark")
                                            bookmarkViewModel.deleteBookmarkMovies(movie.id!!)
                                        } else {
                                            icon = Icons.Filled.Bookmark
                                            helper.showToast("Success Add to Bookmark")
                                            bookmarkViewModel.addBookmarkMovies(movie)
                                        }
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
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

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow {
                            items(movie.genres) {
                                Text(
                                    text = it.name.toString(),
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                        .wrapContentSize()
                                        .background(Color("#DCE3FF".toColorInt()))
                                        .padding(vertical = 4.dp, horizontal = 8.dp),
                                    color = Color("#98B1ED".toColorInt())
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Language",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                )

                                Text(
                                    text = movie.original_language.toString(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W400,
                                    color = Color("#B7B7B7".toColorInt())
                                )

                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Popularity",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                )

                                Text(
                                    text = movie.popularity.toString(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W400,
                                    color = Color("#B7B7B7".toColorInt())
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Release Date",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                )

                                Text(
                                    text = movie.release_date.toString(),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W400,
                                    color = Color("#B7B7B7".toColorInt())
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Description",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Text(
                            text = movie.overview.toString(),
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = Color("#B7B7B7".toColorInt())
                        )
                    }
                }
            }
        }
        is Result.Error -> {
            helper.showToast((state.value as Result.Error).message)
        }
        else -> {}
    }
}