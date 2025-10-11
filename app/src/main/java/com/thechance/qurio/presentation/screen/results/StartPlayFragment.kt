package com.thechance.qurio.presentation.screen.results

import android.os.Bundle
import android.view.View
import com.thechance.qurio.R
import android.widget.Toast
import com.thechance.qurio.databinding.FragmentStartPlayBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPlayPresenter.getQuestions()
        setupListeners()
    }

    private fun setupListeners() {
        binding.checkButton.setOnClickListener {
            startPlayPresenter.onCheckButtonClicked(adapter?.selectedPosition)
        }

        binding.skipButton.setOnClickListener {
            startPlayPresenter.nextQuestion()
        }
    }

    override fun onDestroyView() {
        startPlayPresenter.destroyTimer()
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
    }

    override fun resetAnswers() {
        binding.checkButton.setText("Check")
        binding.skipButton.visibility = View.VISIBLE
        adapter?.showCorrect = false
        adapter?.selectedPosition = null
        adapter?.notifyDataSetChanged()
    }

    override fun updateTimer(secondsLeft: Long, progress: Float) {
        /**
         * todo : here we wanna update timer
         */
    }

    override fun onTimerFinished() {
        showToastMessage("Time's up!")
    }

    override fun showMessage(message: String) {
        showToastMessage(message)
    }

    override fun showEndOfQuestions() {
        showToastMessage("End of Questions")
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
    }
}