package com.thechance.qurio.presentation.domain.repository

interface ExampleRepository {
    suspend fun getData(): String
}