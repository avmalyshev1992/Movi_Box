package com.example.movi_box.domain

import com.example.movi_box.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = MainRepository.filmsDataBase

}