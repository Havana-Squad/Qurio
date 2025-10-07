package com.thechance.qurio.presentation.screen.games_screen

import com.thechance.qurio.R
import com.thechance.qurio.domain.model.GameCategory

fun GameCategory.toUi() = GamesScreenState.GameItemUiState(
    id = id,
    title = title,
    img = R.drawable.kuro
)