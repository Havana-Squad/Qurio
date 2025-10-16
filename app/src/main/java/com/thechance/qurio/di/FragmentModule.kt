package com.thechance.qurio.di

import com.thechance.qurio.presentation.screen.example.ExampleFragment
import com.thechance.qurio.presentation.screen.games_screen.GamesFragment
import com.thechance.qurio.presentation.screen.played_games_screen.PlayedGamesFragment
import com.thechance.qurio.presentation.screen.results.ResultPlayFragment
import com.thechance.qurio.presentation.screen.results.StartPlayFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeExampleFragment(): ExampleFragment

    @ContributesAndroidInjector
    abstract fun contributeGameFragment(): GamesFragment

    @ContributesAndroidInjector
    abstract fun contributeStartPlayFragment(): StartPlayFragment

    @ContributesAndroidInjector
    abstract fun contributeResultPlayFragment(): ResultPlayFragment

    @ContributesAndroidInjector
    abstract fun contributePlayedGamesFragment(): PlayedGamesFragment
}