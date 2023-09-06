package com.example.movi_box.data

import android.content.ContentValues
import android.database.Cursor
import com.example.movi_box.R
import com.example.movi_box.data.dao.FilmDao
import com.example.movi_box.domain.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): List<Film> {
        return filmDao.getCachedFilms()
    }
}