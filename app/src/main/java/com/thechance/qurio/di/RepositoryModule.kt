package com.thechance.qurio.di

import com.thechance.qurio.data.repository.ExampleRepositoryImpl
import com.thechance.qurio.data.repository.TGameRepository
import com.thechance.qurio.data.repository.TGameRepositoryImpl
import com.thechance.qurio.data.repository.GameSessionRepository
import com.thechance.qurio.data.repository.GameSessionRepositoryImpl
import com.thechance.qurio.data.repository.ResultsRepositoryImpl
import com.thechance.qurio.data.repository.UserPreferencesRepositoryImpl
import com.thechance.qurio.domain.repository.ExampleRepository
import com.thechance.qurio.domain.repository.ResultsRepository
import com.thechance.qurio.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindExampleRepository(
        impl: ExampleRepositoryImpl
    ): ExampleRepository

    @Binds
    abstract fun bindResultsRepository(
        impl: ResultsRepositoryImpl
    ): ResultsRepository

    @Binds
    abstract fun bindGameRepository(
        impl: TGameRepositoryImpl
    ): TGameRepository

    @Binds
    abstract fun bindGameSessionRepository(
        impl: GameSessionRepositoryImpl
    ): GameSessionRepository

    @Binds
    abstract fun bindUserPreferencesImpl(
        impl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository
}