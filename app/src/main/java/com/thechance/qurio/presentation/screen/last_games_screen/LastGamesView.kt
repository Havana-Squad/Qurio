package com.thechance.qurio.presentation.screen.last_games_screen

import com.thechance.qurio.domain.model.LastGame
import com.thechance.qurio.presentation.base.BaseView

interface LastGamesView : BaseView {
    fun updateLastGames(games: List<LastGame>)
    fun navigateBack()
    fun startLoading()
    fun stopLoading()
    fun showError()
}