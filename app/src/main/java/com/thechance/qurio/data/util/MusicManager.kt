package com.thechance.qurio.data.util

import android.media.MediaPlayer

class MusicManager(private val player: MediaPlayer) {
    private var currentVolume: Float = 1f
    fun setVolume(volume: Float) {
        currentVolume = volume.coerceIn(0f, 1f)
        player.setVolume(currentVolume, currentVolume)
    }

    fun getVolume(): Float = currentVolume
    fun play() {
        if (!player.isPlaying) player.start()
    }

    fun pause() {
        if (player.isPlaying) player.pause()
    }
}
