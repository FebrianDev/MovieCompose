package com.febrian.moviecatalog.screen.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.febrian.moviecatalog.data.model.Movie
import com.febrian.moviecatalog.screen.item.ItemMovieNowPlaying
import com.febrian.moviecatalog.screen.item.ItemMoviePopular
import com.febrian.moviecatalog.screen.navigation.Screen
import com.febrian.moviecatalog.screen.vm.MovieViewModel
import com.febrian.moviecatalog.utils.Helper
import com.febrian.moviecatalog.utils.ProgressBar
import com.febrian.moviecatalog.utils.Result

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: MovieViewModel = hiltViewModel(),
    helper: Helper = Helper(LocalContext.current)
) {

    viewModel.getPopularMovies()
    viewModel.getNowPlayingMovies()

    val popular = viewModel.popularState.collectAsState()
    val nowPlaying = viewModel.nowPlayingState.collectAsState()
    val loading = viewModel.loading.collectAsState()

    LazyColumn(modifier = Modifier
        .padding(bottom = 56.dp)
        .fillMaxSize()) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Movie Catalog",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )

                Icon(
                    Icons.Filled.Bookmarks,
                    contentDescription = "Bookmarks",
                    modifier = Modifier
                        .padding(end = 16.dp, top = 20.dp)
                        .clickable {
                            navController.navigate(Screen.FavoriteScreen.route)
                        }
                )
            }

            Text(
                text = "Now Playing",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 12.dp)
            )

            if (loading.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressBar()
                }
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                when (nowPlaying.value) {
                    is Result.Success -> {
                        items((nowPlaying.value as Result.Success<List<Movie>>).data) {
                            ItemMovieNowPlaying(navController, it)
                        }
                    }
                    is Result.Error -> {
                        helper.showToast((nowPlaying.value as Result.Error).message)
                    }
                    else -> {}
                }
            }

            Text(
                text = "Popular",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 24.dp)
            )
        }

        if (loading.value) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressBar()
                }
            }
        }

        when (popular.value) {
            is Result.Success -> {
                items((popular.value as Result.Success<List<Movie>>).data) {
                    ItemMoviePopular(navController, it)
                }
            }
            is Result.Error -> {
                helper.showToast((popular.value as Result.Error).message)
            }
            else -> {}
        }

    }

}