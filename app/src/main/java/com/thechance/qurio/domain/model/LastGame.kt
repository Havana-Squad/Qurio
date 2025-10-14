package com.thechance.qurio.domain.model

import kotlinx.datetime.LocalDate
import kotlin.time.Duration

data class LastGame(
    val id: Int,
    val gameName: String,
    val coins: Int,
    val stars: Int,
    val duration: Duration,
    val date: LocalDate
)