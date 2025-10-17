package com.thechance.qurio.presentation.screen.achievements

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment
import com.thechance.qurio.R
import com.thechance.qurio.databinding.DialogAchievementsDescriptionBinding
import com.thechance.qurio.domain.entity.Achievement

class AchievementDescDialogFragment : DialogFragment() {

    private var _binding: DialogAchievementsDescriptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var achievement: Achievement

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

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
        _binding = DialogAchievementsDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = achievement.name
        binding.tvAchievementName.text = achievement.name
        binding.tvAchievementDesc.text = if (achievement.isUnlocked)
            achievement.descriptionUnlocked
        else
            achievement.descriptionLocked

        binding.tvAchievementRequirement.text = achievement.requirement

        binding.imgAchievement.setImageResource(
            if (achievement.isUnlocked) achievement.imageUnlockedRes
            else achievement.imageLockedRes
        )

        binding.imgCelebration.visibility = if (achievement.isUnlocked) View.VISIBLE else View.GONE
        binding.buttonShare.visibility = if (achievement.isUnlocked) View.VISIBLE else View.GONE
        binding.buttonShare.setOnClickListener {
            shareAchievement(achievement)
        }

        binding.buttonExit.setOnClickListener { dismiss() }
        binding.buttonOk.setOnClickListener { dismiss() }
    }

    private fun shareAchievement(achievement: Achievement) {
        val shareText = getString(
            R.string.share_achievement_text,
            achievement.name
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }

        val chooser = Intent.createChooser(intent, getString(R.string.share_via))
        startActivity(chooser)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(achievement: Achievement): AchievementDescDialogFragment {
            return AchievementDescDialogFragment().apply {
                this.achievement = achievement
            }
        }
    }
}