package com.thechance.qurio.domain.model

data class Question(
    val difficulty: String,
    val category: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val type: String
)