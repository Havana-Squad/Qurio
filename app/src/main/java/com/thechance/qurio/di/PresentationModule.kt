package com.thechance.qurio.di

import com.thechance.qurio.domain.repository.GameRepository
import com.thechance.qurio.presentation.screen.games_screen.GamesPresenter
import com.thechance.qurio.presentation.screen.last_games_screen.LastGamesPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {
    companion object{
        @Provides
        @Singleton
        fun provideGamePresenter(
            gameRepository: GameRepository
        ): GamesPresenter {
            return GamesPresenter( gameRepository)
        }

        @Provides
        @Singleton
        fun provideLastGamesPresenter(
            gameRepository: GameRepository
        ): LastGamesPresenter {
            return LastGamesPresenter(gameRepository)
        }
    }
}