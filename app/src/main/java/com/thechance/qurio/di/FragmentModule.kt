package com.thechance.qurio.di

import com.thechance.qurio.presentation.screen.example.ExampleFragment
import com.thechance.qurio.presentation.screen.last_games_screen.LastGamesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeExampleFragment(): ExampleFragment

    @ContributesAndroidInjector
    abstract fun contributeLastGamesFragment(): LastGamesFragment
}