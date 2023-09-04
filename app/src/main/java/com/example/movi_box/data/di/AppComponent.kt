package com.example.movi_box.data.di

import com.example.movi_box.data.di.module.DatabaseModule
import com.example.movi_box.data.di.module.DomainModule
import com.example.movi_box.data.di.module.RemoteModule
import com.example.movi_box.viewmodel.FirstFragmentViewModel
import com.example.movi_box.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась возможность внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: FirstFragmentViewModel)
    //метод для того, чтобы появилась возможность внедрять зависимости в SettingsFragmentViewModel
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}