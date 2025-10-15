package com.thechance.qurio.domain.repository

import com.thechance.qurio.domain.model.GameCategory

interface GameRepository {
   suspend fun getGames(): List<GameCategory>
}