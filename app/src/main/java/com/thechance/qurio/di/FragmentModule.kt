package com.thechance.qurio.di

import com.thechance.qurio.presentation.screen.example.ExampleFragment
import com.thechance.qurio.presentation.screen.results.ResultPlayFragment
import com.thechance.qurio.presentation.screen.results.StartPlayFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeExampleFragment(): ExampleFragment

    @ContributesAndroidInjector
    abstract fun contributeStartPlayFragment(): StartPlayFragment

    @ContributesAndroidInjector
    abstract fun contributeResultPlayFragment(): ResultPlayFragment
}