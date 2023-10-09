package com.example.movi_box.data


import com.example.movi_box.data.dao.FilmDao
import com.example.movi_box.domain.Film
import kotlinx.coroutines.flow.Flow


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        filmDao.insertAll(films)
    }

    fun getAllFromDB(): Flow<List<Film>> = filmDao.getCachedFilms()

}