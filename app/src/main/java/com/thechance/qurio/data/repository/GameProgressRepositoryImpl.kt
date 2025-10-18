package com.thechance.qurio.data.repository

import android.content.Context
import androidx.core.content.edit
import com.thechance.qurio.domain.repository.game.GameProgressRepository
import javax.inject.Inject
import javax.inject.Singleton

class GameProgressRepositoryImpl @Inject constructor(private val context: Context) :
    GameProgressRepository {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override suspend fun getQuizzesCompleted(): Int = prefs.getInt(KEY_QUIZZES_COMPLETED, 0)

    override suspend fun incrementQuizzesCompleted() {
        val currentQuizzesCompleted = getQuizzesCompleted() + 1
        prefs.edit { putInt(KEY_QUIZZES_COMPLETED, currentQuizzesCompleted) }
    }

    override suspend fun getCategoriesPlayed(): Int = prefs.getInt(KEY_CATEGORIES_PLAYED, 0)

    override suspend fun incrementCategoriesPlayed() {
        val currentCategoriesPlayed = getCategoriesPlayed() + 1
        prefs.edit { putInt(KEY_CATEGORIES_PLAYED, currentCategoriesPlayed) }
    }

    override suspend fun getQuestionsAnswered(): Int = prefs.getInt(KEY_QUESTIONS_ANSWERED, 0)

    override suspend fun incrementQuestionsAnswered(){
        val currentQuestionsAnswered = getQuestionsAnswered() + 1
        prefs.edit { putInt(KEY_QUESTIONS_ANSWERED, currentQuestionsAnswered) }
    }

    companion object {
        private const val KEY_QUESTIONS_ANSWERED = "questions_answered"
        private const val KEY_QUIZZES_COMPLETED = "quizzes_completed"
        private const val KEY_CATEGORIES_PLAYED = "categories_played"
        private const val PREFS_NAME = "game_progress_prefs"
    }
}