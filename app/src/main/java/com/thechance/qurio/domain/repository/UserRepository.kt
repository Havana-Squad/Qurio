package com.thechance.qurio.domain.repository.user

import com.thechance.qurio.domain.entity.Character

interface UserRepository {
    suspend fun getUserCharacter(): Character
    suspend fun updateUserCharacter(character: Character)
    suspend fun getUserStatistics(): Triple<Int, Int, Int>
    suspend fun getUserStreak(): Int
    suspend fun updateStreak(streak: Int)
    suspend fun incrementStreak()
    suspend fun addPoints(points: Int)
     suspend fun updateLives(lives: Int)
     suspend fun updatePoints(points: Int)
}