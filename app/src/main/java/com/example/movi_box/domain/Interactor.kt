package com.example.movi_box.domain

//import android.telecom.Call
import com.example.movi_box.API
import com.example.movi_box.data.TmdbApi
import com.example.movi_box.data.MainRepository
import com.example.movi_box.viewmodel.FirstFragmentViewModel
import com.example.movi_box.data.*
import com.example.movi_box.data.Entity.TmdbResultsDto
import com.example.movi_box.utils.Converter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi) {
    //В конструктор мы будм передавать коллбэк из вьюмоделе, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, котороую нужно загрузить (это для пагинации)
    fun getFilmsFromApi(page: Int, callback: FirstFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                callback.onSuccess(Converter.convertApiListToDTOList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
}