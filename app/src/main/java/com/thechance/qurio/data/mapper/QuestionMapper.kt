package com.thechance.qurio.data.mapper

import com.thechance.qurio.data.remote.QuestionDto
import com.thechance.qurio.domain.model.Question

fun QuestionDto.toEntity() = Question(
        category = category ?: "",
        difficulty = difficulty ?: "",
        question = question ?: "",
        correctAnswer = correctAnswer ?: "",
        incorrectAnswers = incorrectAnswers ?: emptyList(),
        type = type ?: "",
    )