package com.thechance.qurio.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.thechance.qurio.data.ApiService
import com.thechance.qurio.data.local.GameSessionDao
import com.thechance.qurio.data.local.QurioDatabase
import com.thechance.qurio.data.util.LoggingInterceptor
import com.thechance.qurio.presentation.main.QurioApp
import com.thechance.qurio.data.remote.service.GameService
import com.thechance.qurio.data.repository.GameRepositoryImpl
import com.thechance.qurio.domain.repository.GameRepository
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
        @Singleton
        fun provideGameRepository(gameService: GameService): GameRepository {
            return GameRepositoryImpl(gameService)
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }

    @Provides
    @Singleton
    fun provideQurioDatabase(application: QurioApp): QurioDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            QurioDatabase::class.java,
            "qurio_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameSessionDao(database: QurioDatabase): GameSessionDao {
        return database.gameSessionDao()
    }

}