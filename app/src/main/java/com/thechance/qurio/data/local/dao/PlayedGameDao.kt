package com.thechance.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thechance.qurio.data.local.model.PlayedGameTableInfo
import com.thechance.qurio.data.local.model.PlayedGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayedGameDao{
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertLastGame(playedGameEntity: PlayedGameEntity)

    @Query("SELECT * FROM ${PlayedGameTableInfo.TABLE_NAME} ORDER BY ${PlayedGameTableInfo.DATE} DESC")
    fun getAllLastGames() : Flow<List<PlayedGameEntity>>
}