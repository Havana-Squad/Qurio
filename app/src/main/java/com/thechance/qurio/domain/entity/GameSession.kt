package com.thechance.qurio.domain.model
data class GameSession(
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val skippedAnswers: Int,
    val stars: Int,
    val totalTimeSeconds: Int,
    val earnedCoins: Int,
) : java.io.Serializable
