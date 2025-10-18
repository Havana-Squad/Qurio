package com.thechance.qurio.domain.repository.game

interface GameProgressRepository {
    suspend fun incrementQuizzesCompleted()
    suspend fun getQuizzesCompleted(): Int
    suspend fun incrementCategoriesPlayed()
    suspend fun getCategoriesPlayed(): Int
    suspend fun incrementQuestionsAnswered()
    suspend fun getQuestionsAnswered(): Int
}