package com.thechance.qurio.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import kotlin.time.Duration

@Entity(tableName = LastGameTableInfo.TABLE_NAME)
data class LastGameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(LastGameTableInfo.ID)
    val id: Int = 0,
    @ColumnInfo(LastGameTableInfo.GAME_NAME)
    val gameName: String,
    @ColumnInfo(LastGameTableInfo.COINS)
    val coins: Int,
    @ColumnInfo(LastGameTableInfo.STARS)
    val stars: Int,
    @ColumnInfo(LastGameTableInfo.DURATION)
    val duration: Duration,
    @ColumnInfo(LastGameTableInfo.DATE)
    val date: LocalDate
)