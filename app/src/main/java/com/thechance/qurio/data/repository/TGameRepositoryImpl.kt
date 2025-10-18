package com.thechance.qurio.data.repository

import com.thechance.qurio.data.ApiService
import com.thechance.qurio.data.mapper.toEntity
import com.thechance.qurio.data.util.safeApiCall
import com.thechance.qurio.domain.model.Question
import javax.inject.Inject

class TGameRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TGameRepository {
    override suspend fun fetchQuestions(
        amount: Int,
        difficulty: String,
        type: String,
        category: Int
    ): List<Question> {
        val apiResponse = safeApiCall {
            apiService.getQuestions(
                amount = amount,
                difficulty = difficulty,
                type = type,
                category = category
            )
        }
        return apiResponse.results
            ?.mapNotNull { questionDto -> questionDto?.toEntity() }
            ?: emptyList()
    }
}