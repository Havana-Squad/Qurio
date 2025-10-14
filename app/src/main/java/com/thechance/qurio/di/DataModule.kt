package com.thechance.qurio.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.thechance.qurio.data.local.QurioDatabase
import com.thechance.qurio.data.local.dao.LastGameDao
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
    companion object {
        const val BASE_URL = "https://opentdb.com/"
        private val contentType = "application/json".toMediaType()
        private const val TIMEOUT = 20L
        const val DATABASE_NAME = "qurio_database"

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
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
        fun provideQurioDatabase(context: Context): QurioDatabase {
            return Room
                .databaseBuilder(context, QurioDatabase::class.java, DATABASE_NAME)
                .build()
        }

        @Provides
        @Singleton
        fun provideLastGameDao(qurioDatabase: QurioDatabase): LastGameDao {
            return qurioDatabase.lastGameDao()
        }
    }
}