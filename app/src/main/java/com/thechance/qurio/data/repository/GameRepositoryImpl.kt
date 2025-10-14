package com.thechance.qurio.data.repository

import com.thechance.qurio.data.local.dao.LastGameDao
import com.thechance.qurio.data.local.mapper.toDataEntity
import com.thechance.qurio.data.local.mapper.toDomain
import com.thechance.qurio.domain.model.LastGame
import com.thechance.qurio.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class GameRepositoryImpl (private val lastGameDao: LastGameDao) : GameRepository {
    override fun getAllLastGames(): Flow<List<LastGame>> {
        return lastGameDao.getAllLastGames().map {
            it.map { lastGame -> lastGame.toDomain() }
        }
    }

    override suspend fun addLastGame(lastGame: LastGame) {
        lastGameDao.insertLastGame(lastGame.toDataEntity())
    }
}