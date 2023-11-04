package com.febrian.moviecatalog.data.repository

import com.febrian.moviecatalog.data.api.ApiService
import com.febrian.moviecatalog.data.model.Movie
import com.febrian.moviecatalog.utils.Result
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPopularMovies(): Result<List<Movie>> {
        return try {
            Result.Success(apiService.getPopularMovies().results)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getNowPlaying(): Result<List<Movie>> {
        return try {
            Result.Success(apiService.getNowPlayingMovies().results)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getDetailMovies(id: String): Result<Movie> {
        return try {
            Result.Success(apiService.getDetailMovies(id))
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    suspend fun getSearchMovies(query: String): Result<List<Movie>> {
        return try {
            Result.Success(apiService.getSearchMovies(query).results)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

}