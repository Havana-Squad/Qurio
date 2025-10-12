package com.thechance.qurio.presentation.screen.games_screen

import com.thechance.qurio.domain.model.GameCategory
import com.thechance.qurio.presentation.screen.games_screen.GameItem.GameItemDrawables

fun GameCategory.toUi() = GameItem(
    id = id,
    title = title,
    drawables = when{
        title.contains("sport", true) -> GameItemDrawables.SPORTS
        title.contains("science", true) -> GameItemDrawables.SCIENCE
        title.contains("geography", true) -> GameItemDrawables.GEOGRAPHY
        title.contains("nature", true)
                || title.contains("animals", true)-> GameItemDrawables.FOOD
        title.contains("art", true) -> GameItemDrawables.ART
        title.contains("history", true)
                || title.contains("mythology", true)-> GameItemDrawables.HISTORY
        title.contains("knowledge", true) -> GameItemDrawables.GENERAL_KNOWLEDGE
        title.contains("celeb", true)
                || title.contains("politic", true)-> GameItemDrawables.SOCIETY
        title.contains("film", true)
                || title.contains("television", true)
                || title.contains("video", true)-> GameItemDrawables.FILM
        title.contains("Entertainment", true) -> GameItemDrawables.MUSIC
        else -> GameItemDrawables.SOCIETY
    }
)