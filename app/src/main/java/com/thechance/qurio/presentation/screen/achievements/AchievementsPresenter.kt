package com.thechance.qurio.presentation.screen.achievements

import com.thechance.qurio.domain.entity.Achievement
import com.thechance.qurio.domain.repository.AchievementsRepository
import com.thechance.qurio.domain.repository.user.UserRepository
import com.thechance.qurio.presentation.base.BasePresenter
import javax.inject.Inject

class AchievementsPresenter @Inject constructor(
    private val achievementsRepository: AchievementsRepository,
    private val userRepo: UserRepository
): BasePresenter<AchievementsView>() {
    fun loadAchievements() {
        tryToExecute(
            callee = { achievementsRepository.getAllAchievements() },
            onStart = { view.showLoading() },
            onSuccess = { achievements -> view.showAchievements(achievements) },
            onFinish = { view.hideLoading() },
            onError = { e -> view.showError(e.message ?: "Failed to load achievements") }
        )
    }

    fun onAchievementClicked(achievement: Achievement) {
        view.openAchievementDetails(achievement)
    }

    fun unlockAchievement(achievementId: Int) {
        tryToExecute(
            callee = {
                achievementsRepository.unlockAchievement(achievementId)
                achievementsRepository.getAllAchievements()
            },
            onSuccess = { updatedAchievements ->
                view.showAchievements(updatedAchievements)
                val achievementsCounts = achievementsRepository.getUnlockedAchievementsCount()
                userRepo.updateAwards(achievementsCounts)
            },
            onError = { t -> view.showError(t.message ?: "Failed to unlock") }
        )
    }
}