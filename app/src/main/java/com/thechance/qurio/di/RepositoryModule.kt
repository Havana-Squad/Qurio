package com.thechance.qurio.di

import com.thechance.qurio.data.local.dao.LastGameDao
import com.thechance.qurio.data.repository.ExampleRepositoryImpl
import com.thechance.qurio.data.repository.GameRepositoryImpl
import com.thechance.qurio.domain.repository.ExampleRepository
import com.thechance.qurio.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindExampleRepository(
        impl: ExampleRepositoryImpl
    ): ExampleRepository

    @Provides
    @Singleton
    fun provideGameRepository(lastGameDao: LastGameDao): GameRepository {
        return GameRepositoryImpl(lastGameDao)
    }
}