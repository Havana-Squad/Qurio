package com.thechance.qurio.data.remote.service

import com.thechance.qurio.data.remote.dto.GameCategoriesDto
import retrofit2.Response
import retrofit2.http.GET

interface GameService {
    @GET("api_category.php")
    suspend fun getGameCategories(): Response<GameCategoriesDto>
}