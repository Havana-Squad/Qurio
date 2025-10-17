package com.thechance.qurio.domain.repository

import com.thechance.qurio.domain.model.Question

interface ResultsRepository {
    suspend fun getQuestions(amount: Int, difficulty: String, type: String):  List<Question>
}