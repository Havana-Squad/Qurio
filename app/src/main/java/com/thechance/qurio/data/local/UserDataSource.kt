package com.thechance.qurio.data.local
import com.thechance.qurio.domain.entity.Character


object UserDataSource{

    private var currentCharacter: Character?=null

    private var lives: Int = 4
    private var points: Int = 700
    private var awards: Int = 0

    fun getUserCharacter() = currentCharacter
    fun updateUserCharacter(newCharacter: Character) { currentCharacter = newCharacter }

    fun getUserStatistics(): Triple<Int, Int, Int> = Triple(lives, points, awards)
    fun updateLives(newLives: Int) { lives = newLives }

    fun updatePoints(newPoints: Int) { points = newPoints }
    fun addPoints(pointsToAdd: Int) { points += pointsToAdd }
    fun updateAwards(newAwards: Int) { awards = newAwards }
    fun incrementAwards() { awards++ }
}
