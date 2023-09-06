package com.example.movi_box.domain

//import android.telecom.Call
import com.example.movi_box.API
import com.example.movi_box.data.TmdbApi
import com.example.movi_box.data.MainRepository
import com.example.movi_box.viewmodel.FirstFragmentViewModel
import com.example.movi_box.data.*
import com.example.movi_box.data.Entity.TmdbResultsDto
import com.example.movi_box.data.preferenes.PreferenceProvider
import com.example.movi_box.utils.Converter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    fun getFilmsFromApi(page: Int, callback: FirstFragmentViewModel.ApiCallback) {
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод, передаем onSuccess и в этот коллбэк список фильмов
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbFilms)
                //Кладём фильмы в БД
                repo.putToDb(list)

                callback.onSuccess(list)
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.geDefaultCategory()

    fun getFilmsFromDB(): List<Film> = repo.getAllFromDB()
}