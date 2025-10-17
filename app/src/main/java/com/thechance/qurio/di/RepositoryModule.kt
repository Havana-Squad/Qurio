package com.thechance.qurio.di

import com.thechance.qurio.data.local.AchievementsRepositoryImpl
import com.thechance.qurio.data.repository.ExampleRepositoryImpl
import com.thechance.qurio.data.repository.TGameRepository
import com.thechance.qurio.data.repository.TGameRepositoryImpl
import com.thechance.qurio.data.repository.GameSessionRepository
import com.thechance.qurio.data.repository.GameSessionRepositoryImpl
import com.thechance.qurio.data.repository.ResultsRepositoryImpl
import com.thechance.qurio.data.repository.GameRepositoryImpl
import com.thechance.qurio.domain.repository.ExampleRepository
import com.thechance.qurio.domain.repository.ResultsRepository
import com.thechance.qurio.domain.repository.achievements.AchievementsRepository
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
    abstract fun bindGameRepository(
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
}