package com.thechance.qurio.presentation.screen.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.thechance.qurio.R
import com.thechance.qurio.databinding.ItemCharacterBinding
import com.thechance.qurio.domain.entity.Character

class CharacterAdapter(
    private val characters: List<Character>,
    private val onItemClick: (Character) -> Unit,
    private val onLockedClick: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION
    private var confirmedPosition = RecyclerView.NO_POSITION

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character, isSelected: Boolean, isConfirmed: Boolean) {
            binding.tvCharacterName.text = item.name
            binding.imgCharacter.setImageResource(
                if (item.isUnlocked) item.imageUnlockedRes else item.imageLockedRes
            )

            val colorRes = when {
                //isConfirmed -> R.color.primary
                isSelected -> R.color.primary
                else -> R.color.shade_tertiary
            }

            binding.tvCharacterName.setTextColor(
                ContextCompat.getColor(binding.root.context, colorRes)
            )

            binding.root.setOnClickListener {
                if (item.isUnlocked) {
                    onItemClick(item)
                    val previousSelected = selectedPosition
                    selectedPosition = adapterPosition
                    if (previousSelected != RecyclerView.NO_POSITION) notifyItemChanged(previousSelected)
                    notifyItemChanged(selectedPosition)
                } else {
                    onLockedClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = characters[position]
        holder.bind(item, position == selectedPosition, position == confirmedPosition)
    }

    override fun getItemCount() = characters.size

    fun getSelectedCharacter(): Character? =
        if (selectedPosition != RecyclerView.NO_POSITION) characters[selectedPosition] else null

    fun confirmSelection() {
        confirmedPosition = selectedPosition
        notifyDataSetChanged()
    }
    fun setConfirmedPosition(position: Int) {
        confirmedPosition = position
        selectedPosition = position
        notifyDataSetChanged()
    }

    fun cancelSelection() {
        selectedPosition = confirmedPosition
        notifyDataSetChanged()
    }
}
