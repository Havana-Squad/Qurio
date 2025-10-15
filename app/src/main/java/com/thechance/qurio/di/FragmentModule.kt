package com.thechance.qurio.di

import com.thechance.qurio.presentation.screen.example.ExampleFragment
import com.thechance.qurio.presentation.screen.games_screen.GamesFragment
import com.thechance.qurio.presentation.screen.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeExampleFragment(): ExampleFragment

    @ContributesAndroidInjector
    abstract fun contributeGameFragment(): GamesFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}