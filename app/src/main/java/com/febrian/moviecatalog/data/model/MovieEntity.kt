package com.febrian.moviecatalog.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey var id: Int? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var vote_average: String? = null,
)