package com.thechance.qurio.data.repository

import android.content.Context
import androidx.core.content.edit
import com.thechance.qurio.data.local.AchievementsDataSource
import com.thechance.qurio.domain.entity.Achievement
import com.thechance.qurio.domain.repository.AchievementsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class AchievementsRepositoryImpl @Inject constructor(private val context: Context) : AchievementsRepository {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override suspend fun getAllAchievements(): List<Achievement> = withContext(Dispatchers.IO) {
        val unlocked = prefs.getStringSet(KEY_UNLOCKED, emptySet()) ?: emptySet()
        AchievementsDataSource.getAllAchievements(context)
            .map { achievement -> achievement.copy(isUnlocked = unlocked.contains(achievement.id.toString())) }
    }

    override suspend fun getAchievementById(id: Int): Achievement? =
        withContext(Dispatchers.IO) {
            getAllAchievements().firstOrNull { it.id == id }
        }

    override suspend fun unlockAchievement(id: Int) = withContext(Dispatchers.IO) {
        val currentAchievementsSet =
            (prefs.getStringSet(KEY_UNLOCKED, emptySet())?.toMutableSet() ?: mutableSetOf())
        currentAchievementsSet.add(id.toString())
        prefs.edit { putStringSet(KEY_UNLOCKED, currentAchievementsSet) }
    }

    override suspend fun isAchievementUnlocked(id: Int): Boolean = withContext(Dispatchers.IO) {
        (prefs.getStringSet(KEY_UNLOCKED, emptySet()) ?: emptySet()).contains(id.toString())
    }

    companion object {
        private const val PREFS_NAME = "achievements_prefs"
        private const val KEY_UNLOCKED = "unlocked_ids"
    }
}