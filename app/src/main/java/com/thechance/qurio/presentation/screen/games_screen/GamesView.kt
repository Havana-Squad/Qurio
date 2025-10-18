package com.thechance.qurio.presentation.screen.games_screen

import com.thechance.qurio.presentation.base.BaseView

interface GamesView : BaseView {
    fun updateGames(games: List<GameItem>)
    fun navigateBack()
    fun onGameItemClick(gameId: Int)
    fun showLoading()
    fun hideLoading()
    fun showError(error: Throwable)
}