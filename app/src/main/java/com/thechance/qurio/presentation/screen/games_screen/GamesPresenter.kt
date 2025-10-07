package com.thechance.qurio.presentation.screen.games_screen

import com.thechance.qurio.domain.model.GameCategory
import com.thechance.qurio.domain.repository.GameRepository
import com.thechance.qurio.presentation.base.BasePresenter

class GamesPresenter(
    private val gameRepository: GameRepository
) : BasePresenter<GamesView>() {
    init {
        getGames()
    }
    fun getGames() {
        tryToExecute(
            callee = gameRepository::getGames,
            onSuccess = ::onGetGamesSuccess,
            onError = ::onGetGamesError
        )
    }

    private fun onGetGamesSuccess(games: List<GameCategory>) {
        view.updateGames(games.map { it.toUi() })
    }

    private fun onGetGamesError(throwable: Throwable) {

    }
}