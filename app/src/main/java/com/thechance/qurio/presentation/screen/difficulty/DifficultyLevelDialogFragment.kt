package com.thechance.qurio.presentation.screen.difficulty

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment
import com.thechance.qurio.databinding.LayoutDifficultyLevelBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DifficultyLevelDialogFragment : DialogFragment(), DifficultyLevelView {

    private var _binding: LayoutDifficultyLevelBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter : DifficultyLevelPresenter

    private var selectedDifficulty: DifficultyLevel = DifficultyLevel.Medium

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
    ): View {
        _binding = LayoutDifficultyLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        binding.difficultyLevelEasyButton.setOnClickListener {
            updateSelectedDifficulty(DifficultyLevel.Easy)
        }
        binding.difficultyLevelMediumButton.setOnClickListener {
            updateSelectedDifficulty(DifficultyLevel.Medium)
        }
        binding.difficultyLevelHardButton.setOnClickListener {
            updateSelectedDifficulty(DifficultyLevel.Hard)
        }

        binding.buttonConfirm.isEnabled = false
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonExit.setOnClickListener {
            dismiss()
        }
        binding.buttonConfirm.setOnClickListener {
            presenter.setDifficultyLevel(selectedDifficulty.name)
        }
    }

    private fun updateSelectedDifficulty(level: DifficultyLevel) {
        selectedDifficulty = level
        binding.buttonConfirm.isEnabled = true

        binding.difficultyLevelEasyButton.isSelected = level == DifficultyLevel.Easy
        binding.difficultyLevelMediumButton.isSelected = level == DifficultyLevel.Medium
        binding.difficultyLevelHardButton.isSelected = level == DifficultyLevel.Hard
    }

    override fun showDifficultyLevel(level: DifficultyLevel) {
        selectedDifficulty = level
    }

    override fun showLoading() {
        // TODO
    }

    override fun hideLoading() {
        // TODO
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDifficultyLevelSet(success: Boolean) {
        if (success) {
            Toast.makeText(requireContext(), "Difficulty level set!", Toast.LENGTH_SHORT).show()
            dismiss()
        } else {
            showError("Failed to set difficulty level")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        _binding = null
    }
}
