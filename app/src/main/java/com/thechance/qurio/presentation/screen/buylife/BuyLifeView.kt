package com.thechance.qurio.presentation.screen.buylife

import com.thechance.qurio.presentation.base.BaseView

interface BuyLifeView : BaseView {
    fun showLifeCount(count: Int)
    fun showPrice(price: Int)
    fun showLoading()
    fun hideLoading()
    fun onLifeBoughtSuccessfully()
    fun showError(message: String)
    fun onLifeBought(success: Boolean)
    fun enableBuyButton()
    fun disableBuyButton()
}

