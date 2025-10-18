package com.thechance.qurio.presentation.screen.characters

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment
import com.thechance.qurio.databinding.DialogCharacterDescriptionBinding
import com.thechance.qurio.domain.entity.Character
import dagger.android.support.AndroidSupportInjection

class CharacterDescDialogFragment : DialogFragment() {

    private var _binding: DialogCharacterDescriptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var character: Character

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
        _binding = DialogCharacterDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("parent")

        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.setFragmentResultListener("character_bought", viewLifecycleOwner) { _, bundle ->
            val success = bundle.getBoolean("success", false)
            if (success) {
                // Forward the result to HomeFragment
                parentFragmentManager.setFragmentResult("character_bought", bundle)
                dismiss() // Close this dialog too
            }
        }
        binding.tvCharacterName.text = character.name
        binding.tvCharacterAge.text ="Age: ${character.age}"
        binding.tvCharacterDesc.text =character.description

        binding.imgCharacter.setImageResource(
            if (character.isUnlocked) character.imageUnlockedRes
            else character.imageLockedRes
        )
        binding.buttonBuy.visibility = if (character.isUnlocked) View.GONE else View.VISIBLE
        binding.buttonBuy.setOnClickListener {
            showBuyDialog()
        }

        binding.buttonExit.setOnClickListener { showCharacterDialog() }
        binding.buttonOk.setOnClickListener { showCharacterDialog()}
    }


    private fun showBuyDialog() {
        CharacterDialogNavigator.showBuyDialog(parentFragmentManager, character)
    }

    private fun showCharacterDialog() {
        CharacterDialogNavigator.showCharacterList(parentFragmentManager)
        dismiss()
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(character: Character): CharacterDescDialogFragment {
            return CharacterDescDialogFragment().apply {
                this.character = character
            }
        }
    }
}