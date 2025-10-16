package com.thechance.qurio.domain.entity

import androidx.annotation.DrawableRes

data class Character (
    val id: Int,
    val name: String,
    val description: String,
    val age :String,
    @DrawableRes val imageLockedRes: Int,
    @DrawableRes val imageUnlockedRes: Int,
    var isUnlocked: Boolean = false,
    var isCharacterUsed: Boolean = false,
    var price :Int
)