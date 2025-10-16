package com.thechance.qurio.presentation.screen.played_games_screen

import com.thechance.qurio.domain.entity.PlayedGame
import com.thechance.qurio.domain.repository.game.GameRepository
import com.thechance.qurio.presentation.base.BasePresenter
import kotlinx.coroutines.flow.Flow

class PlayedGamesPresenter(
    private val gameRepository: GameRepository
) : BasePresenter<PlayedGamesView>() {
    
    init {
        getLastGames()
    }
    
    fun getLastGames() {
        tryToExecute(
            callee = gameRepository::getAllPlayedGames,
            onSuccess = ::onGetLastGamesSuccess,
            onError = ::onGetLastGamesError,
            onStart = ::onGetLastGamesStart
        )
    }

    private suspend fun onGetLastGamesSuccess(playedGames: Flow<List<PlayedGame>>) {
        playedGames.collect { view.updateLastGames(it.map { it.toUi() }) }
        view.stopLoading()
    }

    private fun onGetLastGamesError(throwable: Throwable) {
        view.stopLoading()
        view.showError()
    }

    private fun onGetLastGamesStart() {
        view.startLoading()
    }
}