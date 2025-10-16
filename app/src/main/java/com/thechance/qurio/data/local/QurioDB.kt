package com.thechance.qurio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GameSession::class], version = 1)
abstract class QurioDatabase : RoomDatabase() {
    abstract fun gameSessionDao(): GameSessionDao

}