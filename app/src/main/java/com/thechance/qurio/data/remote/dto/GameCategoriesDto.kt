package com.thechance.qurio.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameCategoriesDto(
    @SerialName("trivia_categories")
    val triviaCategories: List<GameCategoryDto> = emptyList()
)
@Serializable
data class GameCategoryDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String? = null
)