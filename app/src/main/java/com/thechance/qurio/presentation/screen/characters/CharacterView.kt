package com.thechance.qurio.presentation.screen.characters

import com.thechance.qurio.domain.entity.Character
import com.thechance.qurio.presentation.base.BaseView

interface CharacterView: BaseView {
    fun showCharacters(characters: List<Character>)
    fun openCharacterDetails(character: Character)
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
}