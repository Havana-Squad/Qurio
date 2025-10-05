package com.thechance.qurio.domain.repository

interface ExampleRepository {
    suspend fun getData(): String
}