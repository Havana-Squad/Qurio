package com.thechance.qurio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thechance.qurio.data.local.dao.LastGameDao
import com.thechance.qurio.data.local.model.LastGameEntity

@Database(
    entities = [LastGameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QurioDatabase : RoomDatabase() {
    abstract fun lastGameDao(): LastGameDao
}