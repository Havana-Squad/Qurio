package com.thechance.qurio.presentation.screen.results

import android.os.CountDownTimer
import com.thechance.qurio.data.repository.GameRepository
import com.thechance.qurio.domain.model.Question
import com.thechance.qurio.presentation.base.BasePresenter
import javax.inject.Inject

class StartPlayPresenter @Inject constructor(
    private val gameRepository: GameRepository
) : BasePresenter<StartPlayView>() {

    private var questions: List<Question> = emptyList()
    private var currentIndex = 0
    private var currentAnswers: List<String> = emptyList()
    private var countDownTimer: CountDownTimer? = null
    private val questionTimeMillis = 20_000L
    private var questionChecked = false

    fun getQuestions() {
        tryToExecute(
            callee = {
                gameRepository.fetchQuestions(
                    12,
                    difficulty = "easy",
                    type = "multiple"
                )
            },
            onStart = { view?.showLoading() },
            onSuccess = ::onQuestionsSuccess,
            onError = { view?.showError(it) },
            onFinish = { view?.hideLoading() }
        )
    }

    private fun onQuestionsSuccess(list: List<Question>) {

        questions = list
        view?.hideLoading()
        if (list.isNotEmpty()) {
            view?.showQuestions(list)
            showCurrentQuestion()
        } else {
            view?.showMessage("No questions found")
        }
    }

    private fun showCurrentQuestion() {
        questionChecked = false
        val q = questions[currentIndex]
        currentAnswers = mutableListOf<String>().apply {
            q.correctAnswer?.let { add(it) }
            q.incorrectAnswers?.let { addAll(it.filterNotNull()) }
            shuffle()
        }
        view?.showQuestion(q, "Q ${currentIndex + 1}/${questions.size}")
        view?.showAnswers(currentAnswers)
        view?.resetAnswers()
        startTimer()
    }

    private fun startTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(questionTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                val progress = millisUntilFinished.toFloat() / questionTimeMillis
                view?.updateTimer(secondsLeft, progress)
            }

            override fun onFinish() {
                view?.updateTimer(0, 0f)
                view?.onTimerFinished()
                nextQuestion()
            }
        }.start()
    }

    fun onCheckButtonClicked(selectedPosition: Int?) {
        if (!questionChecked) {
            if (selectedPosition == null) {
                view?.showMessage("Select an answer first")
                return
            }
            val question = questions[currentIndex]
            val correct = question.correctAnswer ?: ""
            view?.highlightAnswers(correct, selectedPosition)
            questionChecked = true
            countDownTimer?.cancel()
        } else {
            nextQuestion()
        }
    }

    fun nextQuestion() {
        countDownTimer?.cancel()
        if (currentIndex < questions.size - 1) {
            currentIndex++
            showCurrentQuestion()
        } else {
            view?.showEndOfQuestions()
        }
    }

    fun destroyTimer() {
        countDownTimer?.cancel()
    }
}