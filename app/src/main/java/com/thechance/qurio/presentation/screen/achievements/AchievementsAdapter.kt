package com.thechance.qurio.presentation.screen.achievements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thechance.qurio.databinding.ItemAchievementBinding
import com.thechance.qurio.domain.entity.Achievement

class AchievementsAdapter(
    private val achievements: List<Achievement>,
    private val onItemClick: (Achievement) -> Unit
) : RecyclerView.Adapter<AchievementsAdapter.AchievementViewHolder>() {

    inner class AchievementViewHolder(
        private val binding: ItemAchievementBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Achievement) {
            binding.tvAchievementName.text = item.name
            binding.imgAchievement.setImageResource(
                if (item.isUnlocked) item.imageUnlockedRes else item.imageLockedRes
            )

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val binding = ItemAchievementBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AchievementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        holder.bind(achievements[position])
    }

    override fun getItemCount() = achievements.size
}
