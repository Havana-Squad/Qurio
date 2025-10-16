package com.thechance.qurio.presentation.model

data class GameUi(
    val id: Int = 0,
    val gameName: String = "",
    val coins: String? = null,
    val stars: String? = null,
    val duration: String? = "",
    val date: String? = ""
)