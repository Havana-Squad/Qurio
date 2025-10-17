package com.thechance.qurio.presentation.screen.settings

import com.thechance.qurio.presentation.base.BasePresenter

class SettingsPresenter : BasePresenter<SettingsView>() {

    private var soundLevel = 100
    private var musicLevel = 100

    fun onSoundChanged(level: Int) {
        soundLevel = level
        view.updateSoundLevel(level)
    }

    fun onMusicChanged(level: Int) {
        musicLevel = level
        view.updateMusicLevel(level)
    }

    fun onSaveClicked() {
        view.showSavedMessage()
    }

    fun onDiscardClicked() {
        soundLevel = 100
        musicLevel = 100
        view.updateSoundLevel(soundLevel)
        view.updateMusicLevel(musicLevel)
        view.showDiscardedMessage()
    }

    fun getCurrentLevels(): Pair<Int, Int> = soundLevel to musicLevel
}
