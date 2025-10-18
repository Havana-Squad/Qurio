package com.thechance.qurio.data.repository

import android.content.Context
import com.thechance.qurio.data.local.CharactersDataSource
import com.thechance.qurio.data.local.UserDataSource
import com.thechance.qurio.domain.entity.Character
import com.thechance.qurio.domain.repository.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val context: Context,
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
        return UserDataSource.getUserStreak()
    }

    override suspend fun updateStreak(streak: Int) {
        UserDataSource.updateStreak(streak)
    }

    override suspend fun incrementStreak() {
        UserDataSource.incrementStreak()
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

}