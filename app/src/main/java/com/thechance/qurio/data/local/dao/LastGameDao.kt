package com.thechance.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thechance.qurio.data.local.model.LastGameTableInfo
import com.thechance.qurio.data.local.model.LastGameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LastGameDao{
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertLastGame(lastGameEntity: LastGameEntity)

    @Query("SELECT * FROM ${LastGameTableInfo.TABLE_NAME} ORDER BY ${LastGameTableInfo.DATE} DESC")
    fun getAllLastGames() : Flow<List<LastGameEntity>>
}