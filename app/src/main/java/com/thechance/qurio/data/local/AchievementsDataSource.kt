package com.thechance.qurio.data.local

import android.content.Context
import com.thechance.qurio.R
import com.thechance.qurio.domain.entity.Achievement

object AchievementsDataSource {

    fun getAllAchievements(context: Context): List<Achievement> = listOf(
        Achievement(
            id = 1,
            name = context.getString(R.string.achievement_quiz_rookie_name),
            descriptionLocked = context.getString(R.string.achievement_quiz_rookie_locked),
            descriptionUnlocked = context.getString(R.string.achievement_quiz_rookie_unlocked),
            imageLockedRes = R.drawable.quiz_rookie_shadow,
            imageUnlockedRes = R.drawable.quiz_rookie,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_quiz_rookie_requirement)
        ),
        Achievement(
            id = 2,
            name = context.getString(R.string.achievement_streak_starter_name),
            descriptionLocked = context.getString(R.string.achievement_streak_starter_locked),
            descriptionUnlocked = context.getString(R.string.achievement_streak_starter_unlocked),
            imageLockedRes = R.drawable.streak_starter_shadow,
            imageUnlockedRes = R.drawable.streak_starter,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_streak_starter_requirement)
        ),
        Achievement(
            id = 3,
            name = context.getString(R.string.achievement_lucky_guess_name),
            descriptionLocked = context.getString(R.string.achievement_lucky_guess_locked),
            descriptionUnlocked = context.getString(R.string.achievement_lucky_guess_unlocked),
            imageLockedRes = R.drawable.lucky_guess_shadow,
            imageUnlockedRes = R.drawable.lucky_guess,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_lucky_guess_requirement)
        ),
        Achievement(
            id = 4,
            name = context.getString(R.string.achievement_explorer_name),
            descriptionLocked = context.getString(R.string.achievement_explorer_locked),
            descriptionUnlocked = context.getString(R.string.achievement_explorer_unlocked),
            imageLockedRes = R.drawable.explorer_shadow,
            imageUnlockedRes = R.drawable.explorer,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_explorer_requirement)
        ),
        Achievement(
            id = 5,
            name = context.getString(R.string.achievement_trivia_champ_name),
            descriptionLocked = context.getString(R.string.achievement_trivia_champ_locked),
            descriptionUnlocked = context.getString(R.string.achievement_trivia_champ_unlocked),
            imageLockedRes = R.drawable.trivia_champ_shadow,
            imageUnlockedRes = R.drawable.trivia_champ,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_trivia_champ_requirement)
        ),
        Achievement(
            id = 6,
            name = context.getString(R.string.achievement_collector_name),
            descriptionLocked = context.getString(R.string.achievement_collector_locked),
            descriptionUnlocked = context.getString(R.string.achievement_collector_unlocked),
            imageLockedRes = R.drawable.collector_shadow,
            imageUnlockedRes = R.drawable.collector,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_collector_requirement)
        ),
        Achievement(
            id = 7,
            name = context.getString(R.string.achievement_legend_name),
            descriptionLocked = context.getString(R.string.achievement_legend_locked),
            descriptionUnlocked = context.getString(R.string.achievement_legend_unlocked),
            imageLockedRes = R.drawable.legend_shadow,
            imageUnlockedRes = R.drawable.legend,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_legend_requirement)
        ),
        Achievement(
            id = 8,
            name = context.getString(R.string.achievement_untouchable_name),
            descriptionLocked = context.getString(R.string.achievement_untouchable_locked),
            descriptionUnlocked = context.getString(R.string.achievement_untouchable_unlocked),
            imageLockedRes = R.drawable.untouchable_shadow,
            imageUnlockedRes = R.drawable.untouchable,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_untouchable_requirement)
        ),
        Achievement(
            id = 9,
            name = context.getString(R.string.achievement_quick_thinker_name),
            descriptionLocked = context.getString(R.string.achievement_quick_thinker_locked),
            descriptionUnlocked = context.getString(R.string.achievement_quick_thinker_unlocked),
            imageLockedRes = R.drawable.quick_thinker_shadow,
            imageUnlockedRes = R.drawable.quick_thinker,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_quick_thinker_requirement)
        ),
        Achievement(
            id = 10,
            name = context.getString(R.string.achievement_perfect_game_name),
            descriptionLocked = context.getString(R.string.achievement_perfect_game_locked),
            descriptionUnlocked = context.getString(R.string.achievement_perfect_game_unlocked),
            imageLockedRes = R.drawable.collector_shadow,
            imageUnlockedRes = R.drawable.collector,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_perfect_game_requirement)
        ),
        Achievement(
            id = 11,
            name = context.getString(R.string.achievement_knowledge_seeker_name),
            descriptionLocked = context.getString(R.string.achievement_knowledge_seeker_locked),
            descriptionUnlocked = context.getString(R.string.achievement_knowledge_seeker_unlocked),
            imageLockedRes = R.drawable.lucky_guess2_shadow,
            imageUnlockedRes = R.drawable.lucky_guess2,
            isUnlocked = true,
            requirement = context.getString(R.string.achievement_knowledge_seeker_requirement)
        )
    )
}