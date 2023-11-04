package com.febrian.moviecatalog.di

import android.content.Context
import com.febrian.moviecatalog.utils.Helper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideHelper(@ApplicationContext context: Context): Helper = Helper(context)
}