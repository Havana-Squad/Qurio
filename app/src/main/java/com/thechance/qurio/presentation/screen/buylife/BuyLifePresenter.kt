package com.thechance.qurio.presentation.screen.buylife

import com.thechance.qurio.domain.repository.user.UserRepository
import com.thechance.qurio.presentation.base.BasePresenter
import javax.inject.Inject

class BuyLifePresenter @Inject constructor(
    private val userRepository: UserRepository
) : BasePresenter<BuyLifeView>() {

    private var lifeCount: Int = 1
    private var price: Int = 200
    private var userPoints: Int = 0

    fun loadLifeInfo() {
        tryToExecute(
            callee = { userRepository.getUserStatistics() },
            onSuccess = ::onLoadLifeInfoSuccess,
            onError = ::onError
        )
    }

    private fun onLoadLifeInfoSuccess(statistics: Triple<Int, Int, Int>) {
        val (_, points, _) = statistics
        userPoints = points

        view.showLifeCount(lifeCount)
        view.showPrice(price)

        if (userPoints < price) {
            view.showError("Not enough points to buy a life!")
            view.disableBuyButton()
        } else {
            view.enableBuyButton()
        }
    }

    fun buyLife() {
        view.showLoading()

        tryToExecute(
            callee = { userRepository.getUserStatistics() },
            onSuccess = ::onBuyLifeSuccess,
            onError = ::onBuyLifeError
        )
    }

    private suspend fun onBuyLifeSuccess(statistics: Triple<Int, Int, Int>) {
        val (lives, points, _) = statistics

        if (points >= price) {
            userRepository.updateLives(lives + 1)
            userRepository.updatePoints(points - price)
            view.hideLoading()
            view.onLifeBoughtSuccessfully()
        } else {
            view.hideLoading()
            view.showError("Not enough points to buy a life!")
            view.disableBuyButton()
            view.onLifeBought(false)
        }
    }

    private fun onBuyLifeError(throwable: Throwable) {
        view.hideLoading()
        view.showError(throwable.message ?: "Failed to buy life")
        view.onLifeBought(false)
    }

    private fun onError(throwable: Throwable) {
        view.showError(throwable.message ?: "Failed to load life info")
    }
}