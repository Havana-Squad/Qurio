package com.thechance.qurio.presentation.screen.games_screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.thechance.qurio.R
import com.thechance.qurio.databinding.FragmentGamesBinding
import com.thechance.qurio.presentation.base.BaseFragment
import jakarta.inject.Inject

class GamesFragment() : BaseFragment<FragmentGamesBinding, GamesView, GamesPresenter>(), GamesView {
    @Inject
    override lateinit var presenter: GamesPresenter

    override val layoutIdFragment: Int
        get() = R.layout.fragment_games

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        callbacks()
    }

    fun callbacks() {
        binding.iconGoBack.setOnClickListener {
            navigateBack()
        }
    }

    override fun onGameItemClick(gameId: Int) {
        //navigate to play screen
        TODO("Not yet implemented")
    }

    override fun updateGames(games: List<GamesScreenState.GameItemUiState>) {
        val adapter = GamesAdapter(games, this)
        binding.gamesRecycler.adapter = adapter
    }

    override fun navigateBack() {
        findNavController().popBackStack()
    }
}