package com.thechance.qurio.presentation.screen.achievements

import com.thechance.qurio.domain.entity.Achievement
import com.thechance.qurio.domain.repository.AchievementsRepository
import com.thechance.qurio.domain.repository.game.GameProgressRepository
import com.thechance.qurio.domain.repository.user.UserRepository
import jakarta.inject.Inject

class AchievementsManager @Inject constructor(
    private val gameProgressRepository: GameProgressRepository,
    private val achievementsRepository: AchievementsRepository,
    private val userRepository: UserRepository
) {

    suspend fun checkAndUnlockAchievements(
        maxCorrectStreak: Int,
        firstAnswerCorrect: Boolean,
        scorePercent: Int,
        totalQuestions: Int,
        correctAnswers: Int,
        fastestCorrectAnswerTime: Long
    ): List<Achievement> {
        val unlocked = mutableListOf<Achievement>()

        checkQuizzesCompleted(unlocked)
        checkCategoriesPlayed(unlocked)
        checkStreaks(maxCorrectStreak, unlocked)
        checkLuckyGuess(firstAnswerCorrect, unlocked)
        checkTriviaChamp(scorePercent, unlocked)
        checkUntouchable(maxCorrectStreak, unlocked)
        checkQuickThinker(fastestCorrectAnswerTime, unlocked)
        checkPerfectGame(correctAnswers, totalQuestions, unlocked)
        checkKnowledgeSeeker(unlocked)
        checkCollector(unlocked)

        getUserAwardsCount()

        return unlocked
    }

    private suspend fun getUserAwardsCount() {
        val achievementsCount = achievementsRepository.getUnlockedAchievementsCount()
        userRepository.updateAwards(achievementsCount)
    }

    private suspend fun checkQuizzesCompleted(unlocked: MutableList<Achievement>) {
        gameProgressRepository.incrementQuizzesCompleted()
        val quizzesCompleted = gameProgressRepository.getQuizzesCompleted()

        if (quizzesCompleted == 5) unlockAchievement(1, unlocked)
        if (quizzesCompleted == 50) unlockAchievement(7, unlocked)
    }

    private suspend fun checkCategoriesPlayed(unlocked: MutableList<Achievement>) {
        gameProgressRepository.incrementCategoriesPlayed()
        if (gameProgressRepository.getCategoriesPlayed() >= 4)
            unlockAchievement(4, unlocked)
    }

    private suspend fun checkStreaks(maxCorrectStreak: Int, unlocked: MutableList<Achievement>) {
        if (maxCorrectStreak >= 3) unlockAchievement(2, unlocked)
    }

    private suspend fun checkLuckyGuess(
        firstAnswerCorrect: Boolean,
        unlocked: MutableList<Achievement>
    ) {
        if (firstAnswerCorrect) unlockAchievement(3, unlocked)
    }

    private suspend fun checkTriviaChamp(scorePercent: Int, unlocked: MutableList<Achievement>) {
        if (scorePercent >= 80) unlockAchievement(5, unlocked)
    }

    private suspend fun checkUntouchable(
        maxCorrectStreak: Int,
        unlocked: MutableList<Achievement>
    ) {
        if (maxCorrectStreak >= 10) unlockAchievement(8, unlocked)
    }

    private suspend fun checkQuickThinker(
        fastestCorrectAnswerTime: Long,
        unlocked: MutableList<Achievement>
    ) {
        if (fastestCorrectAnswerTime < 3000) unlockAchievement(9, unlocked)
    }

    private suspend fun checkPerfectGame(
        correctAnswers: Int,
        totalQuestions: Int,
        unlocked: MutableList<Achievement>
    ) {
        if (totalQuestions > 0 && correctAnswers == totalQuestions)
            unlockAchievement(10, unlocked)
    }

    private suspend fun checkKnowledgeSeeker(unlocked: MutableList<Achievement>) {
        gameProgressRepository.incrementQuestionsAnswered()
        if (gameProgressRepository.getQuestionsAnswered() >= 100)
            unlockAchievement(11, unlocked)
    }

    private suspend fun checkCollector(unlocked: MutableList<Achievement>) {
        val unlockedCount = achievementsRepository.getAllAchievements().count { it.isUnlocked }
        if (unlockedCount >= 5) unlockAchievement(6, unlocked)
    }

    private suspend fun unlockAchievement(id: Int, unlocked: MutableList<Achievement>) {
        if (!achievementsRepository.isAchievementUnlocked(id)) {
            achievementsRepository.unlockAchievement(id)
            achievementsRepository.getAchievementById(id)?.let { unlocked.add(it) }
        }
    }
}
