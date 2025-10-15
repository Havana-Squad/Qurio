package com.thechance.qurio.presentation.screen.achievements

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.thechance.qurio.data.repository.AchievementsRepositoryImpl
import com.thechance.qurio.databinding.DialogAchievementsBinding
import com.thechance.qurio.domain.entity.Achievement

class AchievementsDialogFragment : DialogFragment(), AchievementsView {

    private var _binding: DialogAchievementsBinding? = null
    private val binding get() = _binding!!

    private val presenter by lazy { AchievementsPresenter(AchievementsRepositoryImpl(requireContext())) }

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
        _binding = DialogAchievementsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        binding.recyclerAchievements.adapter = AchievementsAdapter(emptyList()) {
            presenter.onAchievementClicked(it)
        }

        binding.buttonOk.setOnClickListener { dismiss() }
        binding.buttonExit.setOnClickListener { dismiss() }

        presenter.loadAchievements()
    }

    override fun showAchievements(achievements: List<Achievement>) {
        val layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.CENTER
            flexWrap = FlexWrap.WRAP
        }

        binding.recyclerAchievements.layoutManager = layoutManager
        binding.recyclerAchievements.adapter =
            AchievementsAdapter(achievements) { presenter.onAchievementClicked(it) }
    }

    override fun showLoading() {}
    override fun hideLoading() {}

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun openAchievementDetails(achievement: Achievement) {
        showAchievementDialog(achievement)
    }

    private fun showAchievementDialog(achievement: Achievement) {
        dismiss()
        AchievementDescDialogFragment.newInstance(achievement)
            .show(parentFragmentManager, "achievement_desc")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        _binding = null
    }
}
