package com.thechance.qurio.presentation.screen.last_games_screen

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.thechance.qurio.R
import com.thechance.qurio.databinding.FragmentLastGamesBinding
import com.thechance.qurio.presentation.base.BaseFragment
import com.thechance.qurio.presentation.model.GameUi
import jakarta.inject.Inject

class LastGamesFragment : BaseFragment<FragmentLastGamesBinding, LastGamesView, LastGamesPresenter>(), LastGamesView {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_last_games

    @Inject
    override lateinit var presenter: LastGamesPresenter

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
        val adapter = LastGameAdapter(games)
        binding.lastGamesRecycler.adapter = adapter
    }

    override fun navigateBack() {
        findNavController().popBackStack()
    }

    override fun startLoading() {
    }

    override fun stopLoading() {

    }

    override fun showError() {

    }
}