package com.thechance.qurio.presentation.screen.characters
import com.thechance.qurio.domain.entity.Character

object CharacterDialogNavigator {
    fun showCharacterList(fragmentManager: androidx.fragment.app.FragmentManager) {
        CharacterDialogFragment().show(fragmentManager, "CharacterDialog")
    }

    fun showCharacterDetails(
        fragmentManager: androidx.fragment.app.FragmentManager,
        character: Character
    ) {
        CharacterDescDialogFragment.newInstance(character)
            .show(fragmentManager, "CharacterDescDialog")
    }

    fun showBuyDialog(
        fragmentManager: androidx.fragment.app.FragmentManager,
        character: Character
    ) {
        CharacterBuyDialogFragment.newInstance(character)
            .show(fragmentManager, "CharacterBuyDialog")
    }
}