package com.thechance.qurio.presentation.screen.settings

import com.thechance.qurio.presentation.base.BaseView

interface SettingsView : BaseView {
    fun updateSoundLevel(level: Int)
    fun updateMusicLevel(level: Int)
    fun showSavedMessage()
    fun showDiscardedMessage()
}