package com.thechance.qurio.presentation.screen.settings

import android.widget.SeekBar
import android.widget.Toast
import com.thechance.qurio.R
import com.thechance.qurio.databinding.SettingsLayoutBinding
import com.thechance.qurio.presentation.base.BaseFragment

class SettingsFragment : BaseFragment<SettingsLayoutBinding, SettingsView, SettingsPresenter>(),
    SettingsView {

    override val layoutIdFragment = R.layout.settings_layout
    override val presenter = SettingsPresenter()

    private lateinit var soundSeekBar: SeekBar
    private lateinit var musicSeekBar: SeekBar

    override fun setupViews() {
        setTitle(true, getString(R.string.settings))

        soundSeekBar = binding.root.findViewById(R.id.seekbar_sound)
        musicSeekBar = binding.root.findViewById(R.id.seekbar_music)

        val (sound, music) = presenter.getCurrentLevels()
        soundSeekBar.progress = sound
        musicSeekBar.progress = music

        setupSeekBars()
        setupButtons()
    }

    private fun setupSeekBars() {
        soundSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) presenter.onSoundChanged(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        musicSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) presenter.onMusicChanged(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupButtons() {
        binding.buttonSave.setOnClickListener { presenter.onSaveClicked() }
        binding.buttonDiscard.setOnClickListener { presenter.onDiscardClicked() }
    }

    override fun updateSoundLevel(level: Int) {
        soundSeekBar.progress = level
    }

    override fun updateMusicLevel(level: Int) {
        musicSeekBar.progress = level
    }

    override fun showSavedMessage() {
        Toast.makeText(requireContext(), getString(R.string.settings_saved), Toast.LENGTH_SHORT)
            .show()
    }

    override fun showDiscardedMessage() {
        Toast.makeText(requireContext(), getString(R.string.discarded), Toast.LENGTH_SHORT).show()
    }
}
