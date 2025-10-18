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
    private val onItemClick: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character, isSelected: Boolean) {
            binding.tvCharacterName.text = item.name
            binding.imgCharacter.setImageResource(
                if (item.isUnlocked) item.imageUnlockedRes else item.imageLockedRes
            )

            binding.tvCharacterName.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    if (isSelected) R.color.primary else android.R.color.white
                )
            )

            binding.root.setOnClickListener {
                onItemClick(item)
                if (item.isUnlocked) {
                    val previousPosition = selectedPosition
                    selectedPosition = adapterPosition

                    if (previousPosition != RecyclerView.NO_POSITION) {
                        notifyItemChanged(previousPosition)
                    }
                    notifyItemChanged(selectedPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position], position == selectedPosition)
    }

    override fun getItemCount() = characters.size

    fun getSelectedCharacter(): Character? {
        return if (selectedPosition != RecyclerView.NO_POSITION) {
            characters[selectedPosition]
        } else {
            null
        }
    }
}