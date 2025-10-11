package com.thechance.qurio.presentation.screen.games_screen

import com.thechance.qurio.domain.model.GameCategory

fun GameCategory.toUi() = GamesScreenState.GameItemUiState(
    id = id,
    title = title,
    drawables = when{
        title.contains("sport", true) -> GamesScreenState.GameItemDrawablesUiState.SPORTS
        title.contains("science", true) -> GamesScreenState.GameItemDrawablesUiState.SCIENCE
        title.contains("geography", true) -> GamesScreenState.GameItemDrawablesUiState.GEOGRAPHY
        title.contains("nature", true)
                || title.contains("animals", true)-> GamesScreenState.GameItemDrawablesUiState.FOOD
        title.contains("art", true) -> GamesScreenState.GameItemDrawablesUiState.ART
        title.contains("history", true)
                || title.contains("mythology", true)-> GamesScreenState.GameItemDrawablesUiState.HISTORY
        title.contains("knowledge", true) -> GamesScreenState.GameItemDrawablesUiState.GENERAL_KNOWLEDGE
        title.contains("celeb", true)
                || title.contains("politic", true)-> GamesScreenState.GameItemDrawablesUiState.SOCIETY
        title.contains("film", true)
                || title.contains("television", true)
                || title.contains("video", true)-> GamesScreenState.GameItemDrawablesUiState.FILM
        title.contains("Entertainment", true) -> GamesScreenState.GameItemDrawablesUiState.MUSIC
        else -> GamesScreenState.GameItemDrawablesUiState.SOCIETY
    }
)