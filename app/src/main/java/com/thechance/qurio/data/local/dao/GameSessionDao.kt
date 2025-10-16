package com.thechance.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thechance.qurio.data.local.model.GameSession

@Dao
interface GameSessionDao {
    @Insert
    suspend fun insertSession(session: GameSession)

    @Query("SELECT * FROM game_session ORDER BY date ASC")
    suspend fun getAllSessions(): List<GameSession>

    @Query("SELECT SUM(earnedCoins) FROM game_session")
    suspend fun getTotalPointsOfAllSessions(): Int
}