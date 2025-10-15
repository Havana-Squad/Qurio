package com.thechance.qurio.data.repository

import com.thechance.qurio.data.local.GameSession
import com.thechance.qurio.data.local.GameSessionDao
import com.thechance.qurio.data.mapper.toEntity
import com.thechance.qurio.data.mapper.toModel

class GameSessionRepositoryImpl(private val gameSessionDao: GameSessionDao) :
    GameSessionRepository {
    override suspend fun insertSession(session: GameSession) {
        gameSessionDao.insertSession(session.toEntity())
    }

    override suspend fun getAllSessions(): List<GameSession> {
        return gameSessionDao.getAllSessions().map { it.toModel() }
    }
    override suspend fun getTotalPointsOfAllSessions(): Int {
        return gameSessionDao.getTotalPointsOfAllSessions()
    }
}