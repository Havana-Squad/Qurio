package com.thechance.qurio.presentation.screen.characters

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
import com.thechance.qurio.databinding.DialogCharacterDescriptionBinding
import com.thechance.qurio.domain.entity.Character

class CharacterDescDialogFragment : DialogFragment() {

    private var _binding: DialogCharacterDescriptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var character: Character

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
        _binding = DialogCharacterDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = character.name
        binding.tvCharacterName.text = character.name
        binding.tvCharacterDesc.text =character.description

        binding.imgCharacter.setImageResource(
            if (character.isUnlocked) character.imageUnlockedRes
            else character.imageLockedRes
        )
        binding.buttonBuy.visibility = if (character.isUnlocked) View.VISIBLE else View.GONE
        binding.buttonBuy.setOnClickListener {
            buyCharacter(character)
        }

        binding.buttonExit.setOnClickListener { dismiss() }
        binding.buttonOk.setOnClickListener { dismiss() }
    }

    private fun buyCharacter(character: Character) {
        val shareText = getString(
            R.string.share_Character_text,
            Character.name
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
        fun newInstance(Character: Character): CharacterDescDialogFragment {
            return CharacterDescDialogFragment().apply {
                this.character = Character
            }
        }
    }
}