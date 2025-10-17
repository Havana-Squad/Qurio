package com.thechance.qurio.presentation.screen.buylife

import com.thechance.qurio.presentation.base.BasePresenter

class BuyLifePresenter : BasePresenter<BuyLifeView>() {
    private var lifeCount: Int = 1
    private var price: Int = 200

    fun loadLifeInfo() {
        view.showLifeCount(lifeCount)
        view.showPrice(price)
    }

    fun buyLife() {
        view.showLoading()
        // TODO: Add the business logic

        // Simulate success
        view.hideLoading()
        view.onLifeBought(true)
    }
}

