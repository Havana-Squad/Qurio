package com.thechance.qurio.presentation.screen.result_screen

data class ResultUiState(
    val isWinner: Boolean = true,
    val starsNum: Int = 3,
    val coins: Int = 5000,
    val correct: Int = 10,
    val inCorrect: Int = 2,
    val skip: Int = 0
)