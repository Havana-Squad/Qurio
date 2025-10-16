package com.thechance.qurio.presentation.screen.settings

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment
import com.thechance.qurio.R
import com.thechance.qurio.databinding.SettingsLayoutBinding
import dagger.android.support.AndroidSupportInjection
import jakarta.inject.Inject

class SettingsDialogFragment : DialogFragment(),
    SettingsView {

    private lateinit var binding: SettingsLayoutBinding

    @Inject
    lateinit var presenter: SettingsPresenter

    private lateinit var soundSeekBar: SeekBar
    private lateinit var musicSeekBar: SeekBar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        AndroidSupportInjection.inject(this)

        dialog.window?.apply {
            setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            decorView.setPadding(0, 0, 0, 0)
        }
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels - (32 * resources.displayMetrics.density)).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        setupViews()
    }

    private fun setupViews() {
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
        binding.buttonExit.setOnClickListener {
            dismiss()
        }
        binding.buttonSave.setOnClickListener { presenter.onSaveClicked() }
        binding.buttonDiscard.setOnClickListener { presenter.onDiscardClicked() }
    }

    override fun updateSoundLevel(level: Int) {
        soundSeekBar.progress = level
    }

    override fun updateMusicLevel(level: Int) {
        musicSeekBar.progress = level
    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}
