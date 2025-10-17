package com.thechance.qurio.data.repository

import com.thechance.qurio.data.local.UserDataSource
import com.thechance.qurio.domain.entity.Character
import com.thechance.qurio.domain.repository.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUserCharacter(): Character {
        return userDataSource.getUserCharacter()
    }

    override suspend fun updateUserCharacter(character: Character) {
        userDataSource.updateUserCharacter(character)
    }

    override suspend fun getUserStatistics(): Triple<Int, Int, Int> {
        return userDataSource.getUserStatistics()
    }

    override suspend fun getUserStreak(): Int {
        return userDataSource.getUserStreak()
    }

    override suspend fun updateStreak(streak: Int) {
        userDataSource.updateStreak(streak)
    }

    override suspend fun incrementStreak() {
        userDataSource.incrementStreak()
    }

    override suspend fun addPoints(points: Int) {
        userDataSource.addPoints(points)
    }
}