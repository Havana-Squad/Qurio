package com.thechance.qurio.di

import com.thechance.qurio.presentation.main.MainActivity
import com.thechance.qurio.presentation.screen.games_screen.GamesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, PresentationModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: GamesFragment)
}