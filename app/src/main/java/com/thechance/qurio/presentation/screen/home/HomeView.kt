package com.thechance.qurio.presentation.screen.home

import com.thechance.qurio.presentation.base.BaseView
import com.thechance.qurio.presentation.model.GameUi
import com.thechance.qurio.presentation.screen.games_screen.GameItem


interface HomeView: BaseView {
    fun setUserCharacter(character: String)
    fun setUserStatistics(statistics: Triple<Int, Int, Int>)
    fun setUserStreak(streak: Int)
    fun setGames(games: List<GameItem>)
    fun setUserLastGames(lastGames: List<GameUi>)
    fun showErrorMessage(message: String)
    fun navigateToGame(gameId: Int)
}