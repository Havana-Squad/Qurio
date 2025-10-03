package com.thechance.qurio.presentation.data.repository

import com.thechance.qurio.presentation.domain.repository.ExampleRepository
import kotlinx.coroutines.delay

class ExampleRepositoryImpl : ExampleRepository {
    override suspend fun getData(): String {
        delay(2000)
        return "Hello from Repository"
    }
}