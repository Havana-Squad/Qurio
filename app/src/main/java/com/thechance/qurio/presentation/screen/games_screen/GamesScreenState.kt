package com.thechance.qurio.presentation.screen.games_screen

import androidx.annotation.DrawableRes

data class GamesScreenState(
    val isLoading: Boolean,
    val isError: Boolean,
    val games: List<GameItemUiState>
){
    data class GameItemUiState(
        val id: Int,
        val title: String,
        @DrawableRes val img: Int
    )
}