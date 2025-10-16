package com.thechance.qurio.domain.repository.game

import com.thechance.qurio.domain.entity.GameCategory
import com.thechance.qurio.domain.entity.PlayedGame
import kotlinx.coroutines.flow.Flow

interface GameRepository {
   suspend fun getGames(): List<GameCategory>
    fun getAllPlayedGames() : Flow<List<PlayedGame>>
    suspend fun addPlayedGame(playedGame: PlayedGame)
}