package com.febrian.moviecatalog.screen.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.febrian.moviecatalog.data.model.Movie
import com.febrian.moviecatalog.screen.item.ItemMoviePopular
import com.febrian.moviecatalog.screen.vm.MovieViewModel
import com.febrian.moviecatalog.utils.Helper
import com.febrian.moviecatalog.utils.ProgressBar
import com.febrian.moviecatalog.utils.Result

@Composable
fun SearchScreen(
    navController: NavController = rememberNavController(),
    viewModel: MovieViewModel = hiltViewModel(),
    helper: Helper = Helper(LocalContext.current)
) {

    val loading = viewModel.loading.collectAsState()
    val searchState = viewModel.searchState.collectAsState()

    val search = remember {
        mutableStateOf("")
    }

    viewModel.getSearchMovies(search.value)

    LazyColumn(
        modifier = Modifier
            .padding(bottom = 56.dp)
            .fillMaxSize()
    ) {

        item {
            Text(
                text = "Search Movie",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 24.dp, top = 16.dp)
            )

            SearchNews(search)

            if (loading.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressBar()
                }
            }
        }

        when (searchState.value) {
            is Result.Success -> {
                val data = (searchState.value as Result.Success<List<Movie>>).data

                if (data.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (search.value.isEmpty()) "Find the movie" else "Movies is not found!",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                } else {
                    items(data) {
                        ItemMoviePopular(navController, it)
                    }
                }
            }
            is Result.Error -> {
                val message = (searchState.value as Result.Error).message
                helper.showToast(message)
                item {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = message,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
            else -> {}
        }

    }
}

@Composable
fun SearchNews(search: MutableState<String>) {
    OutlinedTextField(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
            .padding(8.dp, 0.dp),
        shape = CircleShape,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search icon",
                modifier = Modifier.padding(start = 4.dp),
                tint = Color("#9C9FA8".toColorInt())
            )
        },
        value = search.value,
        placeholder = { Text(text = "Search News") },
        onValueChange = { newText ->
            search.value = newText
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color("#9C9FA8".toColorInt())
        ),
        singleLine = true
    )
}
