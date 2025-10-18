package com.thechance.qurio.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.thechance.qurio.data.local.CharactersDataSource
import com.thechance.qurio.data.local.UserDataSource
import com.thechance.qurio.domain.entity.Character
import com.thechance.qurio.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val context: Context,
    private val preferences: DataStore<Preferences>
) : UserRepository {


    override suspend fun getUserCharacter(): Character {
        return UserDataSource?.getUserCharacter() ?: CharactersDataSource.getAllCharacters(context=context)[0]
    }

    override suspend fun updateUserCharacter(character: Character) {
        UserDataSource.updateUserCharacter(character)
    }

    override suspend fun getUserStatistics(): Triple<Int, Int, Int> {
        return UserDataSource.getUserStatistics()
    }

    override suspend fun getUserStreak(): Int {
        return preferences.data.map {
            it[KEY_CURRENT_STREAK] ?: 0
        }.first()
    }

    override suspend fun getLastPlayedDate(): LocalDate {
        return preferences.data.map {
            it[KEY_LAST_PLAYED_DATE]?.let { dateString ->
                LocalDate.parse(dateString)
            } ?: LocalDate(1970, 1, 1)
        }.first()
    }

    override suspend fun resetStreak() {
        preferences.edit {
            it[KEY_CURRENT_STREAK] = 1
            it[KEY_LAST_PLAYED_DATE] = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
        }
    }

    override suspend fun incrementStreak() {
        preferences.edit {
            it[KEY_CURRENT_STREAK] = (it[KEY_CURRENT_STREAK] ?: 0) + 1
            it[KEY_LAST_PLAYED_DATE] = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
        }
    }

    override suspend fun addPoints(points: Int) {
        UserDataSource.addPoints(points)
    }

    override suspend fun updateLives(lives: Int) {
        UserDataSource.updateLives(lives)
    }

    override suspend fun updatePoints(points: Int) {
        UserDataSource.updatePoints(points)
    }

    override suspend fun updateAwards(awards: Int) {
        UserDataSource.updateAwards(awards)
    }

    private companion object {
        val KEY_CURRENT_STREAK = intPreferencesKey("current_streak")
        val KEY_LAST_PLAYED_DATE = stringPreferencesKey("last_played_date")
    }
}