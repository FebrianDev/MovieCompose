package com.febrian.moviecatalog.screen.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.febrian.moviecatalog.screen.item.ItemMovieBookmark
import com.febrian.moviecatalog.screen.vm.BookmarkViewModel

@Composable
fun BookmarkScreen(
    navController: NavController = rememberNavController(),
    viewModel: BookmarkViewModel = hiltViewModel()
) {

    viewModel.getBookmarkMovies()

    val bookmarkState = viewModel.bookmarkState.collectAsState()

    LazyColumn(modifier = Modifier.padding(bottom = 56.dp).fillMaxSize()) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.padding(start = 16.dp, top = 20.dp).clickable {
                        navController.popBackStack()
                    }
                )

                Text(
                    text = "Bookmarks",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(start = 8.dp, top = 16.dp)
                )
            }
        }

        items(bookmarkState.value) {
            ItemMovieBookmark(navController, it)
        }
    }
}