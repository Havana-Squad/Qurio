package com.thechance.qurio.presentation.screen.results

import com.thechance.qurio.domain.model.GameSession
import com.thechance.qurio.domain.model.Question
import com.thechance.qurio.presentation.base.BaseView

interface StartPlayView : BaseView {
    fun showQuestions(questions: List<Question>)
    fun showQuestion(question: Question, questionNumber: String)
    fun showAnswers(answers: List<String>)
    fun highlightAnswers(correctAnswer: String, selectedPosition: Int)
    fun resetAnswers()
    fun updateTimer(secondsLeft: Long, progress: Float)
    fun onTimerFinished()
    fun showMessage(message: String)
    fun showEndOfQuestions()
    fun showLoading()
    fun hideLoading()
    fun showError(error: Throwable)

    fun toggleSkipButton(visible: Boolean)

    fun onGameSessionSaved(session: GameSession)
}