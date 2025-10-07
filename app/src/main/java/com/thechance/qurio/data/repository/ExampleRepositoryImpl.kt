package com.thechance.qurio.data.repository

import com.thechance.qurio.domain.repository.ExampleRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor() : ExampleRepository {
    override suspend fun getData(): String {
        delay(2000)
        return "Hello from Repository"
    }
}