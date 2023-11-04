package com.febrian.moviecatalog.screen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.febrian.moviecatalog.data.model.Movie
import com.febrian.moviecatalog.data.repository.MovieRepository
import com.febrian.moviecatalog.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _popularState = MutableStateFlow<Result<List<Movie>>>(Result.Empty)
    val popularState: StateFlow<Result<List<Movie>>> get() = _popularState

    private val _nowPlayingState = MutableStateFlow<Result<List<Movie>>>(Result.Empty)
    val nowPlayingState: StateFlow<Result<List<Movie>>> get() = _nowPlayingState

    private val _detailState = MutableStateFlow<Result<Movie>>(Result.Empty)
    val detailState: StateFlow<Result<Movie>> get() = _detailState

    private val _searchState = MutableStateFlow<Result<List<Movie>>>(Result.Empty)
    val searchState: StateFlow<Result<List<Movie>>> get() = _searchState

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun getPopularMovies() {
        viewModelScope.launch {
            _loading.value = true
            _popularState.value = repository.getPopularMovies()
            _loading.value = false
        }
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            _nowPlayingState.value = repository.getNowPlaying()
        }
    }

    fun getDetailMovie(id: String) {
        viewModelScope.launch {
            _detailState.value = repository.getDetailMovies(id)
        }
    }

    fun getSearchMovies(query: String) {
        viewModelScope.launch {
            _searchState.value = repository.getSearchMovies(query)
        }
    }
}