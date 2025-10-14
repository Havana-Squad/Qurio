package com.thechance.qurio.presentation.screen.difficulty

interface DifficultyLevelView {
    fun showDifficultyLevel(level: DifficultyLevel)
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
    fun onDifficultyLevelSet(success: Boolean)
}
