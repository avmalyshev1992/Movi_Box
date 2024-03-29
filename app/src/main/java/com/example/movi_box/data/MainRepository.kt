package com.example.movi_box.data



import com.example.movi_box.data.dao.FilmDao
import com.example.movi_box.data.Entity.Film
import io.reactivex.rxjava3.core.Observable


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        filmDao.insertAll(films)
    }

    fun getAllFromDB(): Observable<List<Film>> = filmDao.getCachedFilms()

}