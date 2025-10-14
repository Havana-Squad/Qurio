package com.thechance.qurio.domain.repository

import com.thechance.qurio.domain.model.LastGame
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getAllLastGames() : Flow<List<LastGame>>
    suspend fun addLastGame(lastGame: LastGame)
}