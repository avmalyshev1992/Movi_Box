package com.example.movi_box.data.di.module

import android.content.Context
import androidx.room.Room
import com.example.movi_box.data.DatabaseHelper
import com.example.movi_box.data.MainRepository
import com.example.movi_box.data.dao.FilmDao
import com.example.movi_box.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}