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
import com.thechance.qurio.databinding.DialogCharacterBuyBinding
import com.thechance.qurio.domain.entity.Character
import com.thechance.qurio.presentation.screen.home.HomeFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CharacterBuyDialogFragment : DialogFragment(){

    private var _binding: DialogCharacterBuyBinding? = null
    private val binding get() = _binding!!


    private lateinit var character: Character
    @Inject
    lateinit var presenter : CharacterPresenter
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
        _binding = DialogCharacterBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(object : CharacterView {
            override fun showLoading() {}
            override fun hideLoading() {}
            override fun showError(message: String) {}
            override fun showCharacters(characters: List<Character>) {}
            override fun openCharacterDetails(character: Character) {}
        })


        binding.characterImg.setImageResource(character.imageLockedRes)
        binding.textPrice.text = character.price.toString()
        binding.text.text

        binding.buttonBuy.setOnClickListener {
            binding.buttonBuy.isEnabled = false
            presenter.buyCharacter(
                character = character,
                onSuccess = {
                    var fragment = parentFragment
                    while (fragment != null && fragment !is HomeFragment) {
                        fragment = fragment.parentFragment
                    }

                    if (fragment is HomeFragment) {
                        fragment.childFragmentManager.setFragmentResult("character_bought", Bundle().apply {
                            putBoolean("success", true)
                        })
                    }

                    dismiss()
                },
                onError = { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                    binding.buttonBuy.isEnabled = true
                }
            )
        }

        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonExit.setOnClickListener { dismiss()}
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(
            character: Character,
        ): CharacterBuyDialogFragment {
            return CharacterBuyDialogFragment().apply {
                this.character = character
            }
        }
    }
}