package com.thechance.qurio.domain.repository.achievements

import com.thechance.qurio.domain.entity.Achievement

interface AchievementsRepository {
    suspend fun getAllAchievements(): List<Achievement>
    suspend fun getAchievementById(id: Int): Achievement?
    suspend fun unlockAchievement(id: Int)
    suspend fun isAchievementUnlocked(id: Int): Boolean
}