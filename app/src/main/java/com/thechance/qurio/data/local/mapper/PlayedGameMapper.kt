package com.thechance.qurio.data.local.mapper

import com.thechance.qurio.data.local.model.PlayedGameEntity
import com.thechance.qurio.domain.model.PlayedGame

fun PlayedGameEntity.toDomain() = PlayedGame(
    id = id,
    gameName = gameName,
    coins = coins,
    stars = stars,
    duration = duration,
    date = date
)

fun PlayedGame.toDataEntity() = PlayedGameEntity(
    id = id,
    gameName = gameName,
    coins = coins,
    stars = stars,
    duration = duration,
    date = date
)