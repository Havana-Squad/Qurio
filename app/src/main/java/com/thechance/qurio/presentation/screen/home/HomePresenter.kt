package com.thechance.qurio.presentation.screen.home

import com.thechance.qurio.domain.entity.GameCategory
import com.thechance.qurio.domain.entity.PlayedGame
import com.thechance.qurio.domain.repository.AchievementsRepository
import com.thechance.qurio.domain.repository.game.GameRepository
import com.thechance.qurio.presentation.base.BasePresenter
import com.thechance.qurio.presentation.screen.games_screen.toUi
import com.thechance.qurio.presentation.screen.played_games_screen.toUi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
class HomePresenter @Inject constructor(
    private val gameRepository: GameRepository,
    private val achievementsRepository: AchievementsRepository
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
            callee = {
                val achievementsCounts = achievementsRepository.getUnlockedAchievementsCount()
                Triple(4, 5200,achievementsCounts) },
            onSuccess = ::onGetUserStatisticsSuccess,
            onError = ::onError
        )
    }

    private fun onGetUserStatisticsSuccess(statistics: Triple<Int, Int, Int>) {
        view.setUserStatistics(statistics)
    }

    private fun getUserStreak() {
        tryToExecute(
            callee =  { 5 },
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
            callee = { gameRepository.getAllPlayedGames() },
            onSuccess = ::onGetUserLastGamesSuccess,
            onError = ::onError
        )
    }

    private suspend fun onGetUserLastGamesSuccess(lastGames: Flow<List<PlayedGame>>) {
        lastGames.collect {
            view.setUserLastGames(it.take(6).map { it.toUi() })
        }
    }
}