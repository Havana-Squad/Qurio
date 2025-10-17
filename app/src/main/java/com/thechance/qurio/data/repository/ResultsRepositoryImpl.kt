package com.thechance.qurio.data.repository

import com.thechance.qurio.data.ApiService
import com.thechance.qurio.data.mapper.toEntity
import com.thechance.qurio.data.util.safeApiCall
import com.thechance.qurio.domain.model.Question
import com.thechance.qurio.domain.repository.ResultsRepository
import javax.inject.Inject

class ResultsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ResultsRepository {

    override suspend fun getQuestions(
        amount: Int,
        difficulty: String,
        type: String
    ): List<Question> {
        val apiResponse = safeApiCall {
            apiService.getQuestions(
                amount = amount,
                difficulty = difficulty,
                type = type
            )
        }

        return apiResponse.results
            ?.mapNotNull { questionDto -> questionDto?.toEntity() }
            ?: emptyList()
    }
}