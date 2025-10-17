package com.thechance.qurio.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val isFirstAppLaunch: Flow<Boolean>
    suspend fun markAppAsLaunched()
}