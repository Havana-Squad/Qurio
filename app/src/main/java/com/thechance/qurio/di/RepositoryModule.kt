package com.thechance.qurio.di

import com.thechance.qurio.data.repository.AchievementsRepositoryImpl
import com.thechance.qurio.data.repository.ExampleRepositoryImpl
import com.thechance.qurio.data.repository.GameProgressRepositoryImpl
import com.thechance.qurio.data.repository.TGameRepository
import com.thechance.qurio.data.repository.TGameRepositoryImpl
import com.thechance.qurio.data.repository.GameSessionRepository
import com.thechance.qurio.data.repository.GameSessionRepositoryImpl
import com.thechance.qurio.data.repository.ResultsRepositoryImpl
import com.thechance.qurio.data.repository.GameRepositoryImpl
import com.thechance.qurio.domain.repository.AchievementsRepository
import com.thechance.qurio.domain.repository.ExampleRepository
import com.thechance.qurio.domain.repository.ResultsRepository
import com.thechance.qurio.domain.repository.game.GameProgressRepository
import com.thechance.qurio.domain.repository.game.GameRepository
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
    abstract fun bindTGameRepository(
        impl: TGameRepositoryImpl
    ): TGameRepository

    @Binds
    abstract fun bindGameSessionRepository(
        impl: GameSessionRepositoryImpl
    ): GameSessionRepository

    @Binds
    abstract fun bindAchievementsRepository(
        impl: AchievementsRepositoryImpl
    ): AchievementsRepository

    @Binds
    abstract fun bindGameRepository(
        impl: GameRepositoryImpl
    ): GameRepository

    @Binds
    abstract fun bindGameProgressRepository(
        impl: GameProgressRepositoryImpl
    ): GameProgressRepository
}