package com.example.movi_box

import android.app.Application

import com.example.movi_box.data.di.AppComponent
import com.example.movi_box.data.di.DaggerAppComponent


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder().build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}