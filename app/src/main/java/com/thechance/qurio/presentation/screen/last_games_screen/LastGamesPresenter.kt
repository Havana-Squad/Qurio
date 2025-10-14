package com.thechance.qurio.presentation.screen.last_games_screen

import com.thechance.qurio.domain.model.LastGame
import com.thechance.qurio.domain.repository.GameRepository
import com.thechance.qurio.presentation.base.BasePresenter
import kotlinx.coroutines.flow.Flow

class LastGamesPresenter(
    private val gameRepository: GameRepository
) : BasePresenter<LastGamesView>() {
    
    init {
        getLastGames()
    }
    
    fun getLastGames() {
        tryToExecute(
            callee = gameRepository::getAllLastGames,
            onSuccess = ::onGetLastGamesSuccess,
            onError = ::onGetLastGamesError,
            onStart = ::onGetLastGamesStart
        )
    }

    private suspend fun onGetLastGamesSuccess(lastGames: Flow<List<LastGame>>) {
        lastGames.collect { view.updateLastGames(it) }
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