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
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.thechance.qurio.databinding.DialogCharactersBinding
import com.thechance.qurio.domain.entity.Character
import com.thechance.qurio.presentation.screen.home.HomeFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDialogFragment : DialogFragment(), CharacterView {

    private var _binding: DialogCharactersBinding? = null
    private val binding get() = _binding!!
    private var characterAdapter: CharacterAdapter? = null

    @Inject
    lateinit var presenter: CharacterPresenter

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

        binding.buttonOk.setOnClickListener {
            characterAdapter?.cancelSelection()
            dismiss()
        }
        binding.buttonExit.setOnClickListener {
            characterAdapter?.cancelSelection()
            dismiss()
        }
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

        characterAdapter = CharacterAdapter(
            characters,
            onItemClick = { character ->
            },
            onLockedClick = { character ->
                presenter.onCharacterClicked(character)
            }
        )

        val currentUsedCharacter = com.thechance.qurio.data.local.UserDataSource.getUserCharacter()
        val confirmedIndex = characters.indexOfFirst { it.id == currentUsedCharacter?.id }
        if (confirmedIndex != -1) {
            characterAdapter?.setConfirmedPosition(confirmedIndex)
        }

        binding.recyclerCharacters.adapter = characterAdapter
    }


    override fun showLoading() {}
    override fun hideLoading() {}

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun openCharacterDetails(character: Character) {
        dismiss()
        CharacterDialogNavigator.showCharacterDetails(parentFragmentManager, character)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        characterAdapter = null
        _binding = null
    }

    private fun onConfirmClicked() {
        val selected = characterAdapter?.getSelectedCharacter()
        if (selected == null || !selected.isUnlocked) {
            Toast.makeText(
                requireContext(),
                "Please select an unlocked character first",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        characterAdapter?.confirmSelection()

        presenter.useCharacter(selected.id, true)

        lifecycleScope.launch {
            delay(300)
            var fragment = parentFragment
            while (fragment != null && fragment !is HomeFragment) {
                fragment = fragment.parentFragment
            }

            if (fragment is HomeFragment) {
                fragment.childFragmentManager.setFragmentResult(
                    "character_updated",
                    Bundle().apply {
                        putBoolean("success", true)
                    })
            }
            Toast.makeText(
                requireContext(),
                "${selected.name} is now your active character!",
                Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }
    }
}