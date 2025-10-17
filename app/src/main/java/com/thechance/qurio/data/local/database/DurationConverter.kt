package com.thechance.qurio.data.local.database

import androidx.room.TypeConverter
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class DurationConverter {
    @TypeConverter
    fun fromDuration(value: Duration): Long {
        return value.inWholeMilliseconds
    }

    @TypeConverter
    fun toDuration(value: Long): Duration {
        return value.toDuration(DurationUnit.MILLISECONDS)
    }
}