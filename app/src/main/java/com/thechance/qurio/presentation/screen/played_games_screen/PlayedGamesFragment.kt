package com.thechance.qurio.presentation.screen.played_games_screen

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.thechance.qurio.R
import com.thechance.qurio.databinding.FragmentPlayedGamesBinding
import com.thechance.qurio.presentation.base.BaseFragment
import com.thechance.qurio.presentation.model.GameUi
import jakarta.inject.Inject

class PlayedGamesFragment : BaseFragment<FragmentPlayedGamesBinding, PlayedGamesView, PlayedGamesPresenter>(), PlayedGamesView {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_played_games

    @Inject
    override lateinit var presenter: PlayedGamesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun setupViews() {
        binding.iconGoBack.setOnClickListener {
            navigateBack()
        }
    }

    override fun updateLastGames(games: List<GameUi>) {
        val adapter = PlayedGamesAdapter(games)
        binding.lastGamesRecycler.adapter = adapter
    }

    override fun navigateBack() {
        findNavController().navigate(PlayedGamesFragmentDirections.actionPlayedGamesFragmentToHomeFragment())
    }

    override fun startLoading() {
    }

    override fun stopLoading() {

    }

    override fun showError() {

    }
}