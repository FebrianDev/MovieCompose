package com.febrian.moviecatalog.data.repository

import com.febrian.moviecatalog.data.database.MovieDao
import com.febrian.moviecatalog.data.model.MovieEntity
import javax.inject.Inject

class BookmarkRepository @Inject constructor(private val dao: MovieDao) {

    suspend fun getBookmarkMovies(): List<MovieEntity> {
        return dao.getBookmarkMovies()
    }

    fun addBookmarkMovies(movie: MovieEntity) {
        dao.addBookmarkMovies(movie)
    }

    fun deleteBookmarkMovies(id: Int) {
        dao.deleteBookmarkMovies(id)
    }

    fun moviesExist(id: Int): Boolean {
        return dao.moviesExist(id)
    }
}