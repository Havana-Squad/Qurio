package com.thechance.qurio.presentation.screen.difficulty

import javax.inject.Inject

class DifficultyLevelPresenter @Inject constructor() {

    private var view: DifficultyLevelView? = null

    fun attachView(view: DifficultyLevelView) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun setDifficultyLevel(level: String) {
        view?.showLoading()
        // Simulate setting difficulty level
        val success = true // Replace with actual logic
        view?.hideLoading()
        view?.onDifficultyLevelSet(success)
    }
}
