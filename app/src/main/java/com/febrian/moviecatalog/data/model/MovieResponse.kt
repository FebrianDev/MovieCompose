package com.febrian.moviecatalog.data.model

data class MovieResponse(
    var results: List<Movie> = arrayListOf()
)