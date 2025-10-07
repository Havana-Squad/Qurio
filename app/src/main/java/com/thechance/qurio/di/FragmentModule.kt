package com.thechance.qurio.di

import com.thechance.qurio.presentation.screen.example.ExampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeExampleFragment(): ExampleFragment
}