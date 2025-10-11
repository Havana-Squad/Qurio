package com.thechance.qurio.domain.repository

interface StatsRepository {
    suspend fun saveCorrectAnswersCount(count: Int)
    suspend fun saveWrongAnswersCount(count: Int)
    suspend fun saveSkippedQuestionsCount(count: Int)
    suspend fun saveScore(score: Int)
    suspend fun getCorrectAnswersCount(): Int
    suspend fun getWrongAnswersCount(): Int
    suspend fun getSkippedQuestionsCount(): Int
    suspend fun getScore(): Int
    suspend fun resetAllStats()
}