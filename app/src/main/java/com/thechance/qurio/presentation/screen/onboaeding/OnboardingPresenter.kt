package com.thechance.qurio.presentation.screen.onboaeding

import com.thechance.qurio.domain.repository.UserPreferencesRepository
import com.thechance.qurio.presentation.base.BasePresenter
import javax.inject.Inject

class OnboardingPresenter @Inject constructor(
    private val userPreferences: UserPreferencesRepository
) : BasePresenter<OnboardingView>() {

    fun firstAppLaunch() {
        tryToExecute(
            callee = { userPreferences.markAppAsLaunched() },
            onSuccess = {},
            onError = { view.showErrorMessage(it.message?:"Unknown error") }
        )
    }
}