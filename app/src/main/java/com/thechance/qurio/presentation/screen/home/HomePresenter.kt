package com.thechance.qurio.presentation.screen.home

import com.thechance.qurio.domain.model.GameCategory
import com.thechance.qurio.domain.repository.GameRepository
import com.thechance.qurio.presentation.base.BasePresenter
import com.thechance.qurio.presentation.screen.games_screen.toUi
import javax.inject.Inject
class HomePresenter @Inject constructor(
    private val gameRepository: GameRepository
) : BasePresenter<HomeView>() {
    init {
        getUserCharacter()
        getUserStatistics()
        getUserStreak()
        getGames()
        getUserLastGames()
    }

    private fun getUserCharacter() {
        tryToExecute(
            callee = { "Rika" },
            onSuccess = ::onGetUserCharacterSuccess,
            onError = ::onError
        )
    }

    private fun onGetUserCharacterSuccess(character: String) {
        view.setUserCharacter(character)
    }

    private fun onError(throwable: Throwable) {
        view.showErrorMessage(throwable.message?:"Unknown error occurred")
    }

    private fun getUserStatistics() {
        tryToExecute(
            callee = { Triple(4, 5200,2) },
            onSuccess = ::onGetUserStatisticsSuccess,
            onError = ::onError
        )
    }

    private fun onGetUserStatisticsSuccess(statistics: Triple<Int, Int, Int>) {
        view.setUserStatistics(statistics)
    }

    private fun getUserStreak() {
        tryToExecute(
            callee =  { 3 },
            onSuccess = ::onGetUserStreakSuccess,
            onError = ::onError
        )
    }

    private fun onGetUserStreakSuccess(streak: Int) {
        view.setUserStreak(streak)
    }

    private fun getGames() {
        tryToExecute(
            callee = gameRepository::getGames,
            onSuccess = ::onGetGamesSuccess,
            onError = ::onError
        )
    }

    private fun onGetGamesSuccess(games: List<GameCategory>) {
        view.setGames(games.shuffled().take(6).map { it.toUi() })
    }

    private fun getUserLastGames() {
        tryToExecute(
            callee = { listOf("1", "2", "3", "4", "5") },
            onSuccess = ::onGetUserLastGamesSuccess,
            onError = ::onError
        )
    }

    private fun onGetUserLastGamesSuccess(lastGames: List<String>) {
        view.setUserLastGames(lastGames)
    }
}