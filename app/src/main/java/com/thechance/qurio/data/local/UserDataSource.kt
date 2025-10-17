package com.thechance.qurio.data.local

import android.content.Context
import com.thechance.qurio.R
import com.thechance.qurio.domain.entity.Character
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSource @Inject constructor(context: Context) {

    private var currentCharacter: Character = Character(
        id = 1,
        name = context.getString(R.string.rika),
        description = context.getString(R.string.nature_s_little_explorer_rika_talks_to_mushrooms_and_swears_squirrels_give_her_battle_advice_always_ready_for_an_adventure),
        age = context.getString(R.string._12_sunblooms),
        imageLockedRes = R.drawable.rika,
        imageUnlockedRes = R.drawable.rika_selected,
        isUnlocked = true,
        isCharacterUsed = true,
        price = 0,
    )

    private var lives: Int = 4
    private var points: Int = 200
    private var awards: Int = 0
    private var currentStreak: Int = 0

    fun getUserCharacter() = currentCharacter
    fun updateUserCharacter(newCharacter: Character) { currentCharacter = newCharacter }

    fun getUserStatistics(): Triple<Int, Int, Int> = Triple(lives, points, awards)
    fun updateLives(newLives: Int) { lives = newLives }
    fun updatePoints(newPoints: Int) { points = newPoints }
    fun addPoints(pointsToAdd: Int) { points += pointsToAdd }
    fun updateAwards(newAwards: Int) { awards = newAwards }
    fun incrementAwards() { awards++ }

    fun getUserStreak() = currentStreak
    fun updateStreak(newStreak: Int) { currentStreak = newStreak }
    fun incrementStreak() { currentStreak++ }
    fun resetStreak() { currentStreak = 0 }
}
