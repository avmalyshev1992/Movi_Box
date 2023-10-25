package com.example.movi_box.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movi_box.data.dao.FilmDao
import com.example.movi_box.data.Entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}