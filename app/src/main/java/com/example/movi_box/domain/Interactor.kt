package com.example.movi_box.domain

//import android.telecom.Call
import androidx.lifecycle.LiveData
import com.example.movi_box.API
import com.example.movi_box.data.TmdbApi
import com.example.movi_box.data.MainRepository
import com.example.movi_box.viewmodel.FirstFragmentViewModel
import com.example.movi_box.data.*
import com.example.movi_box.data.Entity.TmdbResultsDto
import com.example.movi_box.data.preferenes.PreferenceProvider
import com.example.movi_box.utils.Converter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBarState = Channel<Boolean>(Channel.CONFLATED)

    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        scope.launch {
            progressBarState.send(true)
        }
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                val list = Converter.convertApiListToDTOList(response.body()?.tmdbFilms)
                //Кладем фильмы в бд
                //В случае успешного ответа кладем фильмы в БД и выключаем ProgressBar
                scope.launch {
                    repo.putToDb(list)
                    progressBarState.send(false)
                }
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала выключаем ProgressBar
                scope.launch {
                    progressBarState.send(false)
                }
            }
        })
    }
    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.geDefaultCategory()

    fun getFilmsFromDB(): Flow<List<Film>> = repo.getAllFromDB()
}