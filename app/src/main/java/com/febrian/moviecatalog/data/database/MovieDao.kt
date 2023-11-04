package com.febrian.moviecatalog.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.febrian.moviecatalog.data.model.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getBookmarkMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmarkMovies(movies: MovieEntity)

    @Query("DELETE FROM movie WHERE id=:id")
    fun deleteBookmarkMovies(id: Int)

    @Query("SELECT EXISTS(SELECT * from movie where id = :id)")
    fun moviesExist(id: Int): Boolean
}