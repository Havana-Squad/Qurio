package com.thechance.qurio.presentation.screen.results

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.thechance.qurio.R
import com.thechance.qurio.data.repository.GameSessionRepository
import com.thechance.qurio.databinding.FragmentResultPlayBinding
import com.thechance.qurio.presentation.base.BaseFragment
import com.thechance.qurio.presentation.screen.results.idel.ResultPlayPresenter
import com.thechance.qurio.presentation.screen.results.idel.ResultPlayView
import jakarta.inject.Inject

class ResultPlayFragment :
    BaseFragment<FragmentResultPlayBinding, ResultPlayView, ResultPlayPresenter>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_result_play

    @Inject
    lateinit var resultPlayPresenter: ResultPlayPresenter

    override val presenter: ResultPlayPresenter
        get() = resultPlayPresenter

    @Inject
    lateinit var gameSessionRepository: GameSessionRepository

    private val args: ResultPlayFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        displayResults()
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(
                ResultPlayFragmentDirections.actionResultPlayFragmentToStartPlayFragment(
                    categoryId = args.categoryId
                )
            )
        }
    }

    private fun displayResults() {
        val session = args.session
        val result = args.session
        binding.results.apply {
            setCorrectCount(result.correctAnswers.toString())
            setIncorrectCount(result.wrongAnswers.toString())
            setSkippedCount(result.skippedAnswers.toString())
            setReward(result.earnedCoins.toString())
            showBlur(true)
            showStars(true)
            animateStars()
        }.updateResult(
            correctAnswers = session.correctAnswers,
            totalQuestions = session.correctAnswers + session.wrongAnswers + session.skippedAnswers
        )
    }
}