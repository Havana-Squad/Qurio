package com.thechance.qurio.data.mapper

import com.thechance.qurio.data.local.model.GameSession

fun GameSession.toModel(): GameSession {
    return GameSession(
        correctAnswers = correctAnswers,
        wrongAnswers = wrongAnswers,
        skippedAnswers = skippedAnswers,
        stars = stars,
        totalTimeSeconds = totalTimeSeconds,
        earnedCoins = earnedCoins
    )
}

fun GameSession.toEntity(): GameSession {
    return GameSession(
        correctAnswers = correctAnswers,
        wrongAnswers = wrongAnswers,
        skippedAnswers = skippedAnswers,
        stars = stars,
        totalTimeSeconds = totalTimeSeconds,
        earnedCoins = earnedCoins
    )
}