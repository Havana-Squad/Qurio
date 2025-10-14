package com.thechance.qurio.presentation.screen.difficulty

class DifficultyLevelPresenter {

    private var view: DifficultyLevelView? = null

    fun attachView(view: DifficultyLevelView) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun loadDifficultyInfo() {
        // Simulate loading difficulty level info
        view?.showDifficultyLevel(DifficultyLevel.Medium)
    }

    fun setDifficultyLevel(level: String) {
        view?.showLoading()
        // Simulate setting difficulty level
        val success = true // Replace with actual logic
        view?.hideLoading()
        view?.onDifficultyLevelSet(success)
    }
}
