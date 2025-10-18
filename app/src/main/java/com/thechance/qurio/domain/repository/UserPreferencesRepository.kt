package com.thechance.qurio.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val isFirstAppLaunch: Flow<Boolean>
    suspend fun markAppAsLaunched()
}