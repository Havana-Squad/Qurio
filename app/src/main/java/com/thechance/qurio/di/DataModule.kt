package com.thechance.qurio.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.thechance.qurio.data.ApiService
import com.thechance.qurio.data.local.UserDataSource
import com.thechance.qurio.data.local.dao.GameSessionDao
import com.thechance.qurio.data.util.LoggingInterceptor
import com.thechance.qurio.presentation.main.QurioApp
import com.thechance.qurio.data.remote.service.GameService
import com.thechance.qurio.data.repository.GameRepositoryImpl
import com.thechance.qurio.domain.repository.game.GameRepository
import com.thechance.qurio.data.local.dao.PlayedGameDao
import com.thechance.qurio.data.local.database.QurioDatabase
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {
    companion object{
        const val BASE_URL = "https://opentdb.com/"
        private val contentType = "application/json".toMediaType()
        private const val TIMEOUT = 20L
        const val DATABASE_NAME = "qurio_database"

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().apply {
                addInterceptor(LoggingInterceptor())
                connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                readTimeout(TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            }.build()
        }

        @Provides
        @Singleton
        fun provideJson(): Json {
            return Json { ignoreUnknownKeys = true }
        }

        @Provides
        @Singleton
        fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory(contentType))
                .client(okHttpClient)
                .build()
        }

        @Provides
        @Singleton
        fun provideGameService(retrofit: Retrofit): GameService {
            return retrofit.create(GameService::class.java)
        }

        @Provides
        fun provideContext(application: QurioApp): Context = application.applicationContext

        @Provides
        @Singleton
        fun provideQurioDatabase(context: Context): QurioDatabase {
            return Room
                .databaseBuilder(context, QurioDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        @Singleton
        fun providePlayedGameDao(qurioDatabase: QurioDatabase): PlayedGameDao {
            return qurioDatabase.playedGameDao()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideGameSessionDao(database: QurioDatabase): GameSessionDao {
            return database.gameSessionDao()
        }

    }
}