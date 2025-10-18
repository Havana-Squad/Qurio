package com.thechance.qurio.presentation.screen.onboaeding

import com.thechance.qurio.presentation.base.BaseView

interface OnboardingView : BaseView {
    fun onClickRightArrow()
    fun onClickLeftArrow()
    fun onSwipeUp()
    fun showErrorMessage(message: String)
}