package com.thechance.qurio.data.repository

import com.thechance.qurio.data.local.dao.PlayedGameDao
import com.thechance.qurio.data.local.mapper.toDataEntity
import com.thechance.qurio.data.local.mapper.toDomain
import com.thechance.qurio.data.remote.mapper.toEntity
import com.thechance.qurio.data.remote.service.GameService
import com.thechance.qurio.data.util.safeApiCall
import com.thechance.qurio.domain.model.GameCategory
import com.thechance.qurio.domain.model.PlayedGame
import com.thechance.qurio.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton


@Singleton
class GameRepositoryImpl(
    private val gameService: GameService,
    private val playedGameDao: PlayedGameDao
) : GameRepository {
    override suspend fun getGames(): List<GameCategory> {
        return safeApiCall {
            gameService.getGameCategories()
        }.triviaCategories.map { it.toEntity() }
    }

    override fun getAllPlayedGames(): Flow<List<PlayedGame>> {
        return playedGameDao.getAllLastGames().map {
            it.map { lastGame -> lastGame.toDomain() }
        }
    }

    override suspend fun addPlayedGame(playedGame: PlayedGame) {
        playedGameDao.insertLastGame(playedGame.toDataEntity())
    }
}