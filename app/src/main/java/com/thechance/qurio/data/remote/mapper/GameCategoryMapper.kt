package com.thechance.qurio.data.remote.mapper

import com.thechance.qurio.data.remote.dto.GameCategoryDto
import com.thechance.qurio.domain.entity.GameCategory

fun GameCategoryDto.toEntity() = GameCategory(
    title = name ?: "",
    id = id
)