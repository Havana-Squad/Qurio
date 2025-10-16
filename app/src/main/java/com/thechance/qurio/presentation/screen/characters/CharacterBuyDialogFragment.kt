package com.thechance.qurio.presentation.screen.characters

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.thechance.qurio.data.repository.CharactersRepositoryImpl
import com.thechance.qurio.databinding.DialogCharacterBuyBinding
import com.thechance.qurio.domain.entity.Character
import kotlinx.coroutines.launch

class CharacterBuyDialogFragment : DialogFragment() {

    private var _binding: DialogCharacterBuyBinding? = null
    private val binding get() = _binding!!

    private lateinit var character: Character
    private var onBuyConfirmed: (() -> Unit)? = null
    private val repository by lazy { CharactersRepositoryImpl(requireContext()) }

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
        _binding = DialogCharacterBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.characterImg.setImageResource(character.imageLockedRes)
        binding.textPrice.text = character.price.toString()
        binding.text.text

        binding.buttonBuy.setOnClickListener {
            performPurchase()
            dismiss()
            showCharacterDialog()
        }

        binding.buttonCancel.setOnClickListener {
            dismiss()
            showCharacterDialog()
        }
    }

    private fun performPurchase() {
        binding.buttonBuy.isEnabled = false

        lifecycleScope.launch {
            try {

                repository.updateCharacterLockState(character.id, true)
                Toast.makeText(
                    requireContext(),
                    "Character unlocked successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                onBuyConfirmed?.invoke()
                dismiss()

            } catch (e: Exception) {

                Toast.makeText(
                    requireContext(),
                    "Failed to unlock character: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()

                //todo:check money , if user not have enough money buy button will be disabled
                //todo:Deducting coins from user balance
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showCharacterDialog() {
        dismiss()
        CharacterDialogFragment()
            .show(parentFragmentManager, "Character_dialog")
    }

    companion object {
        fun newInstance(
            character: Character,
            onBuyConfirmed: () -> Unit
        ): CharacterBuyDialogFragment {
            return CharacterBuyDialogFragment().apply {
                this.character = character
                this.onBuyConfirmed = onBuyConfirmed
            }
        }
    }
}