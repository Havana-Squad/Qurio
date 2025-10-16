package com.thechance.qurio.data.util

class SoundManager() {
    private var volume = 1f

    fun setVolume(v: Float) {
        volume = v.coerceIn(0f, 1f)
    }

    fun getVolume(): Float = volume
}