package com.thechance.qurio.data.repository

import com.thechance.qurio.data.local.model.GameSession

interface GameSessionRepository {
    suspend fun insertSession(session: GameSession)
    suspend fun getAllSessions(): List<GameSession>
    suspend fun getTotalPointsOfAllSessions(): Int
}