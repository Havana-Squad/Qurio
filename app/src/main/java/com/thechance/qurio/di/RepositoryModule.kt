package com.thechance.qurio.di

import com.thechance.qurio.data.repository.ExampleRepositoryImpl
import com.thechance.qurio.domain.repository.ExampleRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindExampleRepository(
        impl: ExampleRepositoryImpl
    ): ExampleRepository
}