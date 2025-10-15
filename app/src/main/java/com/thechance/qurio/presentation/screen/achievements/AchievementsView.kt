package com.thechance.qurio.presentation.screen.achievements

import com.thechance.qurio.domain.entity.Achievement
import com.thechance.qurio.presentation.base.BaseView

interface AchievementsView : BaseView {
    fun showAchievements(achievements: List<Achievement>)
    fun openAchievementDetails(achievement: Achievement)
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
}