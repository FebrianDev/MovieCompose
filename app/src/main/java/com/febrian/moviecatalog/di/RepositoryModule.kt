package com.febrian.moviecatalog.di

import com.febrian.moviecatalog.data.api.ApiService
import com.febrian.moviecatalog.data.database.MovieDao
import com.febrian.moviecatalog.data.repository.BookmarkRepository
import com.febrian.moviecatalog.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiService) =
        MovieRepository(apiService)

    @Provides
    @Singleton
    fun provideBookmarkRepository(dao: MovieDao) = BookmarkRepository(dao)
}