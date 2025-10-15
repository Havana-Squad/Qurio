package com.thechance.qurio.di

import com.thechance.qurio.data.repository.ExampleRepositoryImpl
import com.thechance.qurio.data.repository.GameRepository
import com.thechance.qurio.data.repository.GameRepositoryImpl
import com.thechance.qurio.data.repository.GameSessionRepository
import com.thechance.qurio.data.repository.GameSessionRepositoryImpl
import com.thechance.qurio.data.repository.ResultsRepositoryImpl
import com.thechance.qurio.domain.repository.ExampleRepository
import com.thechance.qurio.domain.repository.ResultsRepository
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
        impl: GameRepositoryImpl
    ): GameRepository

    @Binds
    abstract fun bindGameSessionRepository(
        impl: GameSessionRepositoryImpl
    ): GameSessionRepository
}