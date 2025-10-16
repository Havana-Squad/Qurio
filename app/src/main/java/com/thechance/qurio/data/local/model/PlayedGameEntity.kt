package com.thechance.qurio.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import kotlin.time.Duration

@Entity(tableName = PlayedGameTableInfo.TABLE_NAME)
data class PlayedGameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(PlayedGameTableInfo.ID)
    val id: Int = 0,
    @ColumnInfo(PlayedGameTableInfo.GAME_NAME)
    val gameName: String,
    @ColumnInfo(PlayedGameTableInfo.COINS)
    val coins: Int,
    @ColumnInfo(PlayedGameTableInfo.STARS)
    val stars: Int,
    @ColumnInfo(PlayedGameTableInfo.DURATION)
    val duration: Duration,
    @ColumnInfo(PlayedGameTableInfo.DATE)
    val date: LocalDate
)