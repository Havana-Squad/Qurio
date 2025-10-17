package com.thechance.qurio.presentation.screen.results

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.thechance.qurio.R
import com.thechance.qurio.data.repository.GameSessionRepository
import com.thechance.qurio.databinding.FragmentResultPlayBinding
import com.thechance.qurio.domain.entity.PlayedGame
import com.thechance.qurio.domain.model.GameSession
import com.thechance.qurio.domain.repository.game.GameRepository
import com.thechance.qurio.presentation.base.BaseFragment
import com.thechance.qurio.presentation.screen.results.idel.ResultPlayPresenter
import com.thechance.qurio.presentation.screen.results.idel.ResultPlayView
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration

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

    @Inject
    lateinit var gameRepository: GameRepository

    private val args: ResultPlayFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        displayResults()
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(
                ResultPlayFragmentDirections.actionResultPlayFragmentToHomeFragment()
            )
        }

        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(
                ResultPlayFragmentDirections.actionResultPlayFragmentToStartPlayFragment(
                    categoryId = args.categoryId
                )
            )
        }

        binding.btnShare.setOnClickListener {
            shareResults()
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
        savePlayedGame(session)
    }

    private fun shareResults() {
        val session = args.session
        val totalQuestions = session.correctAnswers + session.wrongAnswers + session.skippedAnswers
        val scorePercentage = if (totalQuestions > 0) {
            (session.correctAnswers * 100) / totalQuestions
        } else 0

        val shareText = buildString {
            append("🎮 My Qurio Game Results!\n\n")
            append("✅ Correct: ${session.correctAnswers}/${totalQuestions}\n")
            append("❌ Wrong: ${session.wrongAnswers}\n")
            append("⏭️ Skipped: ${session.skippedAnswers}\n")
            append("⭐ Stars: ${session.stars}/3\n")
            append("🪙 Coins Earned: ${session.earnedCoins}\n")
            append("📊 Score: $scorePercentage%\n\n")
            append("Can you beat my score? 🚀")
        }

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            putExtra(Intent.EXTRA_SUBJECT, "My Qurio Game Results")
        }

        try {
            startActivity(Intent.createChooser(intent, "Share your results"))
            Toast.makeText(requireContext(), "Sharing results...", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "Unable to share results",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun savePlayedGame(session : GameSession){
        this.lifecycleScope.launch {
            val games = gameRepository.getGames()
            gameRepository.addPlayedGame(
                PlayedGame(
                    id = 0,
                    gameName = games.find { it.id == args.categoryId }?.title ?: "",
                    coins = session.earnedCoins,
                    stars = session.stars,
                    duration = session.totalTimeSeconds.toLong().toDuration(DurationUnit.SECONDS),
                    date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                )
            )
        }
    }
}