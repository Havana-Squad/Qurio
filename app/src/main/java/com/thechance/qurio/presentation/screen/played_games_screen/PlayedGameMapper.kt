package com.thechance.qurio.presentation.screen.played_games_screen

import com.thechance.qurio.domain.entity.PlayedGame
import com.thechance.qurio.presentation.model.GameUi
import kotlinx.datetime.LocalDate
import kotlin.time.Duration

fun PlayedGame.toUi() = GameUi(
    id = id,
    gameName = gameName,
    coins = coins.toString(),
    stars = stars.toString(),
    duration = duration.format(),
    date = date.format()
)

private fun Duration.format() : String{
    val totalSeconds = this.inWholeSeconds

    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    return if (minutes > 0) {
        String.format("%dm %02dsec", minutes, seconds)
    } else {
        "${seconds}sec"
    }
}

private fun LocalDate.format(): String {
    val day = dayOfMonth.toString().padStart(2, '0')
    val month = monthNumber.toString().padStart(2, '0')
    return "$day-$month-$year"
}