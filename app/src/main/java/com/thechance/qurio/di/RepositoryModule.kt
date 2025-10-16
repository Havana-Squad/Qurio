package com.thechance.qurio.di

import com.thechance.qurio.data.local.AchievementsRepositoryImpl
import com.thechance.qurio.data.repository.ExampleRepositoryImpl
import com.thechance.qurio.data.repository.GameRepositoryImpl
import com.thechance.qurio.domain.repository.ExampleRepository
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
    abstract fun bindAchievementsRepository(
        impl: AchievementsRepositoryImpl
    ): AchievementsRepository

    @Binds
    abstract fun bindGameRepository(
        impl: GameRepositoryImpl
    ): GameRepository
}