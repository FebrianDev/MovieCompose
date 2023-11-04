package com.febrian.moviecatalog.di

import android.app.Application
import androidx.room.Room
import com.febrian.moviecatalog.data.api.ApiService
import com.febrian.moviecatalog.data.database.MovieDao
import com.febrian.moviecatalog.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideAPi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase =
        Room.databaseBuilder(app, MovieDatabase::class.java, "movie").allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideDao(database: MovieDatabase): MovieDao = database.movieDao()

}