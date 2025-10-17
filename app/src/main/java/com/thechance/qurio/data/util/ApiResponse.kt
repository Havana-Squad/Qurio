package com.thechance.qurio.data.util

import com.thechance.qurio.data.remote.QuestionDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(

    @SerialName("response_code")
    val responseCode: Int? = null,

    @SerialName("results")
    val results: List<QuestionDto?>? = null
)