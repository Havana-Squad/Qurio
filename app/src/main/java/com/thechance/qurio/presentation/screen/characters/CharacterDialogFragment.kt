
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
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.thechance.qurio.databinding.DialogCharactersBinding
import com.thechance.qurio.domain.entity.Character
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class CharacterDialogFragment : DialogFragment(), CharacterView {

    private var _binding: DialogCharactersBinding? = null
    private val binding get() = _binding!!
    private var characterAdapter: CharacterAdapter? = null

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
        _binding = DialogCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        binding.buttonOk.setOnClickListener { dismiss() }
        binding.buttonExit.setOnClickListener { dismiss() }
        binding.buttonConfirm.setOnClickListener { onConfirmClicked() }

        presenter.loadCharacters()
    }

    override fun showCharacters(characters: List<Character>) {
        val layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
            alignItems = AlignItems.CENTER
            flexWrap = FlexWrap.WRAP
        }

        binding.recyclerCharacters.layoutManager = layoutManager

        // Create and store the adapter reference
        characterAdapter = CharacterAdapter(characters) { character ->
            presenter.onCharacterClicked(character)
        }

        binding.recyclerCharacters.adapter = characterAdapter
    }

    override fun showLoading() {}
    override fun hideLoading() {}

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun openCharacterDetails(character: Character) {
        CharacterDialogNavigator.showCharacterDetails(parentFragmentManager, character)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        characterAdapter = null
        _binding = null
    }

    private fun onConfirmClicked() {
        // Get the selected character from the adapter
        val selectedCharacter = characterAdapter?.getSelectedCharacter()

        if (selectedCharacter != null) {
            presenter.useCharacter(selectedCharacter.id, true)
            Toast.makeText(requireContext(), "Character ${selectedCharacter.name} selected!", Toast.LENGTH_SHORT).show()
            dismiss()
        } else {
            Toast.makeText(requireContext(), "Please select an unlocked character first", Toast.LENGTH_SHORT).show()
        }
    }
}