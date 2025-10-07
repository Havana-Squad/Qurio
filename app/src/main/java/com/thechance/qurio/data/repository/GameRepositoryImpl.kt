package com.thechance.qurio.data.repository

import com.thechance.qurio.data.remote.mapper.toEntity
import com.thechance.qurio.data.remote.service.GameService
import com.thechance.qurio.data.util.saveApiCall
import com.thechance.qurio.domain.model.GameCategory
import com.thechance.qurio.domain.repository.GameRepository
import javax.inject.Singleton


@Singleton
class GameRepositoryImpl (private val gameService: GameService) : GameRepository {
    override suspend fun getGames(): List<GameCategory> {
        return saveApiCall {
            gameService.getGameCategories()
        }.triviaCategories.map { it.toEntity() }
    }
}