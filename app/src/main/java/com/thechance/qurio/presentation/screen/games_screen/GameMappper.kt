package com.thechance.qurio.presentation.screen.games_screen

import com.thechance.qurio.R
import com.thechance.qurio.domain.model.GameCategory

fun GameCategory.toUi() = GamesScreenState.GameItemUiState(
    id = id,
    title = title,
    img = when{
        title.contains("sport", true) -> R.drawable.img_game_category_sports
        title.contains("science", true) -> R.drawable.img_game_category_science
        title.contains("geography", true) -> R.drawable.img_game_category_geography
        title.contains("nature", true)
                || title.contains("animals", true)-> R.drawable.img_game_category_food
        title.contains("art", true) -> R.drawable.img_game_category_art
        title.contains("history", true)
                || title.contains("mythology", true)-> R.drawable.img_game_category_history
        title.contains("knowledge", true) -> R.drawable.img_game_category_general_knowledge
        title.contains("celeb", true)
                || title.contains("politic", true)-> R.drawable.img_game_category_society
        title.contains("film", true)
                || title.contains("television", true)
                || title.contains("video", true)-> R.drawable.img_game_category_films
        title.contains("Entertainment", true) -> R.drawable.img_game_category_music
        else -> R.drawable.img_game_category_society
    }
)