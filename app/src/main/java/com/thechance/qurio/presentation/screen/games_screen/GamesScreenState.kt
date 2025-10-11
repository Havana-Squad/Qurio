package com.thechance.qurio.presentation.screen.games_screen

import androidx.annotation.DrawableRes
import com.thechance.qurio.R

data class GamesScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val games: List<GameItemUiState>
) {
    data class GameItemUiState(
        val id: Int,
        val title: String,
        val drawables: GameItemDrawablesUiState
    )

    enum class GameItemDrawablesUiState(
        @DrawableRes val img: Int,
        @DrawableRes val shadow: Int,
    ) {
        ART(
            img = R.drawable.img_game_category_art,
            shadow = R.drawable.game_item_shadow_blue
        ),
        FILM(
            img = R.drawable.img_game_category_films,
            shadow = R.drawable.game_item_shadow_blue
        ),
        FOOD(
            img = R.drawable.img_game_category_food,
            shadow = R.drawable.game_item_shadow_yellow
        ),
        GENERAL_KNOWLEDGE(
            img = R.drawable.img_game_category_general_knowledge,
            shadow = R.drawable.game_item_shadow_orange
        ),
        GEOGRAPHY(
            img = R.drawable.img_game_category_geography,
            shadow = R.drawable.game_item_shadow_green
        ),
        HISTORY(
            img = R.drawable.img_game_category_history,
            shadow = R.drawable.game_item_shadow_orange
        ),
        MUSIC(
            img = R.drawable.img_game_category_music,
            shadow = R.drawable.game_item_shadow_purple
        ),
        SCIENCE(
            img = R.drawable.img_game_category_science,
            shadow = R.drawable.game_item_shadow_yellow
        ),
        SOCIETY(
            img = R.drawable.img_game_category_society,
            shadow = R.drawable.game_item_shadow_green
        ),
        SPORTS(
            img = R.drawable.img_game_category_sports,
            shadow = R.drawable.game_item_shadow_blue
        ),
    }
}