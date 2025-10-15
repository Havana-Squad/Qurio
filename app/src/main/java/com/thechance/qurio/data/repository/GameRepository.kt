package com.thechance.qurio.data.repository

import com.thechance.qurio.domain.model.Question

interface GameRepository {
    suspend fun fetchQuestions(amount: Int, difficulty: String, type: String, category: Int): List<Question>
}