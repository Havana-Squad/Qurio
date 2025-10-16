package com.thechance.qurio.presentation.screen.games_screen

import com.thechance.qurio.domain.entity.GameCategory
import com.thechance.qurio.domain.repository.game.GameRepository
import com.thechance.qurio.presentation.base.BasePresenter
import javax.inject.Inject

class GamesPresenter @Inject constructor(
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
        view.updateGames(games.shuffled().map { it.toUi() })
    }

    private fun onGetGamesError(throwable: Throwable) {

    }
}