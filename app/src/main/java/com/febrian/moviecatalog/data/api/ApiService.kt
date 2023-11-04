package com.febrian.moviecatalog.data.api

import com.febrian.moviecatalog.data.model.Movie
import com.febrian.moviecatalog.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular?api_key=bb54f8e346cdc5632fd42f0edb42f3a2")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/now_playing?api_key=bb54f8e346cdc5632fd42f0edb42f3a2")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/{id}?api_key=bb54f8e346cdc5632fd42f0edb42f3a2")
    suspend fun getDetailMovies(
        @Path("id") id: String
    ): Movie

    @GET("search/movie?api_key=bb54f8e346cdc5632fd42f0edb42f3a2&query=query")
    suspend fun getSearchMovies(
        @Query("query") query: String
    ): MovieResponse
}