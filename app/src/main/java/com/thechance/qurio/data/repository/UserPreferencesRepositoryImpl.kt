package com.thechance.qurio.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.thechance.qurio.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val preferencesDataStore: DataStore<Preferences>,
) : UserPreferencesRepository {
    override val isFirstAppLaunch: Flow<Boolean>
        get() {
            return preferencesDataStore.data.map {
                it[KEY_IS_FIRST_LAUNCH] ?: true
            }
        }

    override suspend fun markAppAsLaunched() {
        preferencesDataStore.edit {
            it[KEY_IS_FIRST_LAUNCH] ?: false
        }
    }

    private companion object {
        val KEY_IS_FIRST_LAUNCH = booleanPreferencesKey("first_launch")
    }
}