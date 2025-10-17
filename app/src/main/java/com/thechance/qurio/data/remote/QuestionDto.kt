package com.thechance.qurio.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    @SerialName("difficulty")
    val difficulty: String? = null,
    @SerialName("category")
    val category: String? = null,
    @SerialName("question")
    val question: String? = null,
    @SerialName("correct_answer")
    val correctAnswer: String? = null,
    @SerialName("incorrect_answers")
    val incorrectAnswers: List<String>? = null,
    @SerialName("type")
    val type: String? = null,
)