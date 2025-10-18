package com.thechance.qurio.presentation.screen.results

import android.os.CountDownTimer
import com.thechance.qurio.data.repository.GameSessionRepository
import com.thechance.qurio.data.repository.TGameRepository
import com.thechance.qurio.domain.model.GameSession
import com.thechance.qurio.domain.model.Question
import com.thechance.qurio.domain.repository.user.UserRepository
import com.thechance.qurio.presentation.base.BasePresenter
import com.thechance.qurio.presentation.screen.achievements.AchievementsManager
import com.thechance.qurio.presentation.screen.difficulty.DifficultyLevel
import javax.inject.Inject

class StartPlayPresenter @Inject constructor(
    private val tGameRepository: TGameRepository,
    private val gameSessionRepository: GameSessionRepository,
    private val achievementsManager: AchievementsManager,
    private val userRepository: UserRepository
) : BasePresenter<StartPlayView>() {

    private var questions: List<Question> = emptyList()
    private var currentIndex = 0
    private var currentAnswers: List<String> = emptyList()
    private var countDownTimer: CountDownTimer? = null
    private val questionTimeMillis = 20_000L
    private var questionChecked = false

    private var correctCount = 0
    private var wrongCount = 0
    private var totalTimeSeconds = 0L
    private var timerStartTime = 0L

    private var currentStreak = 0
    private var longestStreak = 0
    private var firstAnswerCorrect = false
    private var fastestAnswerTime = Long.MAX_VALUE


    fun getQuestions(categoryId: Int, difficulty: DifficultyLevel) {
        tryToExecute(
            callee = { tGameRepository.fetchQuestions(12, difficulty.name.lowercase(), "multiple", categoryId) },
            onStart = { view.showLoading() },
            onSuccess = ::onQuestionsSuccess,
            onError = { view.showError(it) },
            onFinish = { view.hideLoading() }
        )
    }

    private fun onQuestionsSuccess(list: List<Question>) {
        questions = list
        view.hideLoading()
        if (list.isNotEmpty()) {
            view.showQuestions(list)
            showCurrentQuestion()
        } else {
            view.showMessage("No questions found")
        }
    }

    private fun showCurrentQuestion() {
        questionChecked = false
        val q = questions[currentIndex]
        currentAnswers = mutableListOf<String>().apply {
            add(q.correctAnswer)
            addAll(q.incorrectAnswers.filterNotNull())
            shuffle()
        }
        view.showQuestion(q, "Q ${currentIndex + 1}/${questions.size}")
        view.showAnswers(currentAnswers)
        view.resetAnswers()
        val isLast = currentIndex == questions.size - 1
        view.toggleSkipButton(!isLast)

        timerStartTime = System.currentTimeMillis()
        startTimer()
    }

    private fun startTimer() {
        countDownTimer?.cancel()

        val durationSeconds = (questionTimeMillis / 1000).toInt()
        view.updateTimer(durationSeconds.toLong(), 1f)

        countDownTimer = object : CountDownTimer(questionTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                val progress = millisUntilFinished.toFloat() / questionTimeMillis
            }

            override fun onFinish() {
                view.onTimerFinished()
                nextQuestion()
            }
        }.start()
    }

    fun onCheckButtonClicked(selectedPosition: Int?) {
        if (!questionChecked) {
            if (selectedPosition == null) {
                view.showMessage("Select an answer first")
                return
            }

            val question = questions[currentIndex]
            val correct = question.correctAnswer
            view.highlightAnswers(correct, selectedPosition)
            questionChecked = true
            countDownTimer?.cancel()

            val timeTaken = System.currentTimeMillis() - timerStartTime
            val answer = currentAnswers.getOrNull(selectedPosition)

            if (answer == correct) {
                correctCount++
                currentStreak++
                if (currentStreak > longestStreak) longestStreak = currentStreak
                if (currentIndex == 0) firstAnswerCorrect = true
                if (timeTaken < fastestAnswerTime) fastestAnswerTime = timeTaken
            } else {
                wrongCount++
                currentStreak = 0
            }


            totalTimeSeconds += ((System.currentTimeMillis() - timerStartTime) / 1000).toInt()

            if (currentIndex == questions.size - 1) {
                saveGameSession()
                view.showEndOfQuestions()
            }

        } else {
            if (currentIndex < questions.size - 1) {
                nextQuestion()
            }
        }
    }

    fun nextQuestion() {
        countDownTimer?.cancel()
        if (currentIndex < questions.size - 1) {
            currentIndex++
            showCurrentQuestion()
        } else {
            saveGameSession()
            view.showEndOfQuestions()
        }
    }

    private fun calculateStars(): Int {
        return when {
            correctCount == questions.size -> 3
            correctCount >= questions.size / 2 -> 2
            correctCount > 0 -> 1
            else -> 0
        }
    }

    private fun calculateCoins(): Int = correctCount * 10

    private fun saveGameSession() {
        val skipped = (questions.size - (correctCount + wrongCount))
        val stars = calculateStars()

        val session = com.thechance.qurio.data.local.model.GameSession(
            correctAnswers = correctCount,
            wrongAnswers = wrongCount,
            skippedAnswers = skipped,
            stars = stars,
            totalTimeSeconds = totalTimeSeconds.toInt(),
            earnedCoins = calculateCoins(),
            date = System.currentTimeMillis()
        )

        tryToExecute(
            callee = { gameSessionRepository.insertSession(session) },
            onStart = {},
            onSuccess = {
                val domainSession = GameSession(
                    correctAnswers = session.correctAnswers,
                    wrongAnswers = session.wrongAnswers,
                    skippedAnswers = session.skippedAnswers,
                    stars = session.stars,
                    totalTimeSeconds = session.totalTimeSeconds,
                    earnedCoins = session.earnedCoins
                )
                if (stars == 0) {
                    tryToExecute(
                        callee = { decreaseLives() },
                        onSuccess = {
                            checkAchievements()
                            view.onGameSessionSaved(domainSession)
                        },
                        onError = { view.showError(it) }
                    )
                } else {
                    checkAchievements()
                    view.onGameSessionSaved(domainSession)
                }
            },
            onError = { view.showError(it) },
            onFinish = {}
        )
    }

    private suspend fun decreaseLives() {
        val stats = userRepository.getUserStatistics()
        val currentLives = stats.first

        if (currentLives > 0) {
            userRepository.updateLives(currentLives - 1)
        }
    }

    private fun checkAchievements() {
        tryToExecute(
            callee = {
                achievementsManager.checkAndUnlockAchievements(
                    maxCorrectStreak = getLongestStreak(),
                    firstAnswerCorrect = wasFirstAnswerCorrect(),
                    scorePercent = calculateScorePercent(),
                    totalQuestions = questions.size,
                    correctAnswers = correctCount,
                    fastestCorrectAnswerTime = getFastestAnswerTime()
                )
            },
            onSuccess = { unlockedAchievements ->
                unlockedAchievements.forEach { achievement ->
                    view.showMessage("Achievement unlocked: ${achievement.name}")
                }
            },
            onError = { throwable ->
                view.showError(throwable)
            }
        )
    }

    private fun getLongestStreak(): Int = longestStreak

    private fun wasFirstAnswerCorrect(): Boolean = firstAnswerCorrect

    private fun calculateScorePercent(): Int {
        if (questions.isEmpty()) return 0
        return ((correctCount.toDouble() / questions.size) * 100).toInt()
    }

    private fun getFastestAnswerTime(): Long =
        if (fastestAnswerTime == Long.MAX_VALUE) 0 else fastestAnswerTime


    fun destroyTimer() {
        countDownTimer?.cancel()
    }
    fun loadCurrentLives() {
        tryToExecute(
            callee = { userRepository.getUserStatistics() },
            onSuccess = { stats ->
                val currentLives = stats.first
                view.updateLivesDisplay(currentLives)
            },
            onError = { view.showError(it) }
        )
    }
}