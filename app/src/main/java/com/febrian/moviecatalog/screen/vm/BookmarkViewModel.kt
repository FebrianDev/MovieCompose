package com.febrian.moviecatalog.screen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febrian.moviecatalog.data.model.Movie
import com.febrian.moviecatalog.data.model.MovieEntity
import com.febrian.moviecatalog.data.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val bookmarkRepository: BookmarkRepository) :
    ViewModel() {

    private val _bookmarkState = MutableStateFlow<List<MovieEntity>>(arrayListOf())
    val bookmarkState: StateFlow<List<MovieEntity>> get() = _bookmarkState

    fun getBookmarkMovies() {
        viewModelScope.launch {
            _bookmarkState.value = bookmarkRepository.getBookmarkMovies()
        }
    }

    fun addBookmarkMovies(movie: Movie) {
        val movieEntity = MovieEntity(
            id = movie.id,
            overview = movie.overview,
            original_language = movie.original_language,
            original_title = movie.original_title,
            popularity = movie.popularity,
            poster_path = movie.poster_path,
            release_date = movie.release_date,
            vote_average = movie.release_date
        )
        bookmarkRepository.addBookmarkMovies(movieEntity)
    }

    fun deleteBookmarkMovies(id: Int) {
        bookmarkRepository.deleteBookmarkMovies(id)
    }

    fun moviesExist(id: Int): Boolean {
        return bookmarkRepository.moviesExist(id)
    }
}