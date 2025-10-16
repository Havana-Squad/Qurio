package com.thechance.qurio.domain.repository

import com.thechance.qurio.domain.model.GameCategory
import com.thechance.qurio.domain.model.LastGame
import kotlinx.coroutines.flow.Flow

interface GameRepository {
   suspend fun getGames(): List<GameCategory>
    fun getAllLastGames() : Flow<List<LastGame>>
    suspend fun addLastGame(lastGame: LastGame)
}