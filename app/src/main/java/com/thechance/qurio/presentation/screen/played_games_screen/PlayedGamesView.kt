package com.thechance.qurio.presentation.screen.played_games_screen

import com.thechance.qurio.presentation.base.BaseView
import com.thechance.qurio.presentation.model.GameUi

interface PlayedGamesView : BaseView {
    fun updateLastGames(games: List<GameUi>)
    fun navigateBack()
    fun startLoading()
    fun stopLoading()
    fun showError()
}