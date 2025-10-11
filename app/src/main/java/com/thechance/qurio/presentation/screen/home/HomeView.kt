package com.thechance.qurio.presentation.screen.home

import com.thechance.qurio.presentation.base.BaseView


interface HomeView: BaseView {
    fun setUserCharacter(character: String)
    fun setUserStatistics(statistics: Triple<Int, Int, Int>)
    fun setUserStreak(streak: Int)
    fun setGames(games: List<String>)
    fun setUserLastGames(lastGames: List<String>)
    fun showErrorMessage(message: String)
}