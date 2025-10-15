package com.thechance.qurio.domain.entity

import androidx.annotation.DrawableRes

data class Achievement(
    val id: Int,
    val name: String,
    val descriptionLocked: String,
    val descriptionUnlocked: String,
    @DrawableRes val imageLockedRes: Int,
    @DrawableRes val imageUnlockedRes: Int,
    val requirement: String,
    var isUnlocked: Boolean = false
)