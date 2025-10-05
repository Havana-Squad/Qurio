package com.thechance.qurio.data.repository

import com.thechance.qurio.domain.repository.ExampleRepository
import kotlinx.coroutines.delay

class ExampleRepositoryImpl : ExampleRepository {
    override suspend fun getData(): String {
        delay(2000)
        return "Hello from Repository"
    }
}