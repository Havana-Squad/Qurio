package com.thechance.qurio.data.local.mapper

import com.thechance.qurio.data.local.model.LastGameEntity
import com.thechance.qurio.domain.model.LastGame

fun LastGameEntity.toDomain() = LastGame(
    id = id,
    gameName = gameName,
    coins = coins,
    stars = stars,
    duration = duration,
    date = date
)

fun LastGame.toDataEntity() = LastGameEntity(
    id = id,
    gameName = gameName,
    coins = coins,
    stars = stars,
    duration = duration,
    date = date
)