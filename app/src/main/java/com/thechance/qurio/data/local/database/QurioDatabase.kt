package com.thechance.qurio.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thechance.qurio.data.local.dao.PlayedGameDao
import com.thechance.qurio.data.local.model.PlayedGameEntity

@Database(
    entities = [PlayedGameEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(DurationConverter::class, LocalDateConverter::class)
abstract class QurioDatabase : RoomDatabase() {
    abstract fun playedGameDao(): PlayedGameDao
}