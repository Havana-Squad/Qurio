package com.thechance.qurio.presentation.screen.settings

import com.thechance.qurio.presentation.base.BasePresenter
import javax.inject.Inject

class SettingsPresenter @Inject constructor() : BasePresenter<SettingsView>() {

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
        //TODO("save settings")
        view.dismiss()
    }

    fun onDiscardClicked() {
        soundLevel = 100
        musicLevel = 100
        view.updateSoundLevel(soundLevel)
        view.updateMusicLevel(musicLevel)
        view.dismiss()
    }

    fun getCurrentLevels(): Pair<Int, Int> = soundLevel to musicLevel
}
