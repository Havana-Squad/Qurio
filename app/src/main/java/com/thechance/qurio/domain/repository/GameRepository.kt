package com.thechance.qurio.domain.repository

import com.thechance.qurio.domain.model.GameCategory
import com.thechance.qurio.domain.model.PlayedGame
import kotlinx.coroutines.flow.Flow

interface GameRepository {
   suspend fun getGames(): List<GameCategory>
    fun getAllPlayedGames() : Flow<List<PlayedGame>>
    suspend fun addPlayedGame(playedGame: PlayedGame)
}