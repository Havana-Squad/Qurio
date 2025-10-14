package com.thechance.qurio.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thechance.qurio.data.local.dao.LastGameDao
import com.thechance.qurio.data.local.model.LastGameEntity

@Database(
    entities = [LastGameEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DurationConverter::class, LocalDateConverter::class)
abstract class QurioDatabase : RoomDatabase() {
    abstract fun lastGameDao(): LastGameDao
}