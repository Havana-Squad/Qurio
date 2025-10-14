package com.thechance.qurio.presentation.screen.last_games_screen

import com.thechance.qurio.presentation.base.BaseView

interface LastGamesView : BaseView {
    fun updateLastGames(games: List<LastGameUi>)
    fun navigateBack()
    fun startLoading()
    fun stopLoading()
    fun showError()
}