package com.thechance.qurio.presentation.screen.results

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.thechance.qurio.R
import com.thechance.qurio.databinding.FragmentStartPlayBinding
import com.thechance.qurio.domain.model.GameSession
import com.thechance.qurio.domain.model.Question
import com.thechance.qurio.presentation.base.BaseFragment
import jakarta.inject.Inject


class StartPlayFragment :
    BaseFragment<FragmentStartPlayBinding, StartPlayView, StartPlayPresenter>(), StartPlayView {

    override val layoutIdFragment: Int get() = R.layout.fragment_start_play

    override val presenter: StartPlayPresenter
        get() = startPlayPresenter

    @Inject
    lateinit var startPlayPresenter: StartPlayPresenter

    private var adapter: QuestionAdapter? = null
    private val args: StartPlayFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPlayPresenter.getQuestions(args.categoryId, args.difficultyLevel)
        presenter.loadCurrentLives()
        setupListeners()
    }

    private fun setupListeners() {
        binding.checkButton.setOnClickListener {
            startPlayPresenter.onCheckButtonClicked(adapter?.selectedPosition)
        }

        binding.skipButton.setOnClickListener {
            startPlayPresenter.nextQuestion()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(StartPlayFragmentDirections.actionStartPlayFragmentToHomeFragment())
        }
    }

    override fun onDestroyView() {
        startPlayPresenter.destroyTimer()
        binding.customTimeComponent.stopTimer()
        super.onDestroyView()
    }

    override fun showQuestions(questions: List<Question>) {
        binding.gameLayout.visibility = View.VISIBLE
    }

    override fun showQuestion(
        question: Question,
        questionNumber: String
    ) {
        binding.questionsPager.setQuestion(question.question ?: "")
        binding.questionsPager.setQuestionNumber(questionNumber)
    }

    override fun showAnswers(answers: List<String>) {
        adapter = QuestionAdapter(answers) {}
        binding.answersLayout.adapter = adapter
    }

    override fun highlightAnswers(correctAnswer: String, selectedPosition: Int) {
        val isCorrect = adapter?.answers?.getOrNull(selectedPosition) == correctAnswer
        binding.scoreIndicator.showScore(isCorrect)
        adapter?.correctAnswer = correctAnswer
        adapter?.showCorrect = true
        adapter?.notifyDataSetChanged()
        binding.checkButton.setText("Next")
        binding.skipButton.visibility = View.GONE

        binding.customTimeComponent.stopTimer()
    }

    override fun resetAnswers() {
        binding.checkButton.setText("Check")
        binding.skipButton.visibility = View.VISIBLE
        adapter?.showCorrect = false
        adapter?.selectedPosition = null
        adapter?.notifyDataSetChanged()
    }

    override fun updateTimer(secondsLeft: Long, progress: Float) {
        binding.customTimeComponent.startTimer(
            durationSeconds = secondsLeft.toInt(),
            onTick = {},
            onComplete = {}
        )
    }

    override fun onTimerFinished() {
        showToastMessage("Time's up!")
        binding.customTimeComponent.stopTimer()
    }

    override fun showMessage(message: String) {
        showToastMessage(message)
    }

    override fun showEndOfQuestions() {
        binding.checkButton.visibility = View.GONE
        binding.customTimeComponent.stopTimer()
    }

    override fun onGameSessionSaved(session: GameSession) {
        val action = StartPlayFragmentDirections
            .actionStartPlayFragmentToResultPlayFragment(session, args.categoryId, args.difficultyLevel)
        findNavController().navigate(action)
    }

    private fun showToastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), message, duration).show()
    }

    override fun showLoading() {
        binding.loadingLayout.visibility = View.VISIBLE
        binding.errorLayout.visibility = View.GONE
        binding.gameLayout.visibility = View.GONE
    }

    override fun hideLoading() {
        binding.gameLayout.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
    }

    override fun showError(error: Throwable) {
        binding.errorLayout.visibility = View.VISIBLE
        binding.gameLayout.visibility = View.GONE
        binding.loadingLayout.visibility = View.GONE
        binding.customTimeComponent.stopTimer()
    }

    override fun toggleSkipButton(visible: Boolean) {
        binding.skipButton.visibility = if (visible) View.VISIBLE else View.GONE
    }
    override fun updateLivesDisplay(lives: Int) {
        binding.livesCount.text = lives.toString()
    }
}