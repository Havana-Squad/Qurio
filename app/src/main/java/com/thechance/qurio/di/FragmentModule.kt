package com.thechance.qurio.di

import com.thechance.qurio.presentation.screen.achievements.AchievementsDialogFragment
import com.thechance.qurio.presentation.screen.buylife.BuyLifeDialogFragment
import com.thechance.qurio.presentation.screen.characters.CharacterBuyDialogFragment
import com.thechance.qurio.presentation.screen.characters.CharacterDescDialogFragment
import com.thechance.qurio.presentation.screen.characters.CharacterDialogFragment
import com.thechance.qurio.presentation.screen.difficulty.DifficultyLevelDialogFragment
import com.thechance.qurio.presentation.screen.example.ExampleFragment
import com.thechance.qurio.presentation.screen.games_screen.GamesFragment
import com.thechance.qurio.presentation.screen.played_games_screen.PlayedGamesFragment
import com.thechance.qurio.presentation.screen.results.ResultPlayFragment
import com.thechance.qurio.presentation.screen.results.StartPlayFragment
import com.thechance.qurio.presentation.screen.home.HomeFragment
import com.thechance.qurio.presentation.screen.settings.SettingsDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeExampleFragment(): ExampleFragment

    @ContributesAndroidInjector
    abstract fun contributeGameFragment(): GamesFragment

    @ContributesAndroidInjector
    abstract fun contributeStartPlayFragment(): StartPlayFragment

    @ContributesAndroidInjector
    abstract fun contributeResultPlayFragment(): ResultPlayFragment

    @ContributesAndroidInjector
    abstract fun contributePlayedGamesFragment(): PlayedGamesFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsDialogFragment(): SettingsDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeAchievementsDialogFragment(): AchievementsDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeBuyLifeDialogFragment(): BuyLifeDialogFragment
    @ContributesAndroidInjector
    abstract fun contributeCharacterDialogFragment(): CharacterDialogFragment
    @ContributesAndroidInjector
    abstract fun contributeCharacterDescDialogFragment(): CharacterDescDialogFragment
    @ContributesAndroidInjector
    abstract fun contributeCharacterBuyDialogFragment(): CharacterBuyDialogFragment
    @ContributesAndroidInjector
    abstract fun contributeDifficultyLevelDialogFragment(): DifficultyLevelDialogFragment
}