package com.febrian.moviecatalog.data.model

data class Movie(
    var id: Int? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var genres: List<Genre> = listOf(),
    var popularity: Double? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var vote_average: String? = null,
)