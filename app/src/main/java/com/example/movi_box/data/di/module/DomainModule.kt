package com.example.movi_box.data.di.module

import android.content.Context
import com.example.movi_box.data.MainRepository
import com.example.movi_box.data.TmdbApi
import com.example.movi_box.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
//Передаем контекст для SharedPreferences через конструктор
class DomainModule(val context: Context) {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) =
        Interactor(repo = repository, retrofitService = tmdbApi)
}