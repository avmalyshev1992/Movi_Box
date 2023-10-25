package com.example.movi_box.utils

import com.example.remote_module.entity.TmdbFilm
import com.example.movi_box.data.Entity.Film

object Converter {
    fun convertApiListToDTOList(list: List<com.example.remote_module.entity.TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(
                Film(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                rating = it.voteAverage,
                isInFavorites = false
            )
            )
        }
        return result
    }
}