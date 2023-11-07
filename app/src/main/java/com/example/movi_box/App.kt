package com.example.movi_box

import android.app.Application
import com.example.movi_box.data.di.AppComponent
import com.example.movi_box.data.di.DaggerAppComponent
import com.example.movi_box.data.di.module.DatabaseModule
import com.example.movi_box.data.di.module.DomainModule
import com.example.remote_module.DaggerRemoteComponent


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        val remoteProvider = DaggerRemoteComponent.create()
        dagger = DaggerAppComponent.builder()
            .remoteProvider(remoteProvider)
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}