package com.thechance.qurio.presentation.screen.characters

import com.thechance.qurio.domain.entity.Character
import com.thechance.qurio.domain.repository.CharactersRepository
import com.thechance.qurio.domain.repository.user.UserRepository
import com.thechance.qurio.presentation.base.BasePresenter
import javax.inject.Inject

class CharacterPresenter @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val userRepository: UserRepository
): BasePresenter<CharacterView>() {
    fun loadCharacters() {
        tryToExecute(
            callee = { charactersRepository.getAllCharacters() },
            onStart = { view.showLoading() },
            onSuccess = { characters -> view.showCharacters(characters) },
            onFinish = { view.hideLoading() },
            onError = { e -> view.showError(e.message ?: "Failed to load Characters") }
        )
    }

    fun onCharacterClicked(character: Character) {
        view.openCharacterDetails(character)
    }

    fun unlockCharacter(characterId: Int , isUnLocked : Boolean)  {
        tryToExecute(
            callee = {
                charactersRepository.updateCharacterLockState(characterId , isUnLocked )
                charactersRepository.getAllCharacters()
            },
            onSuccess = { updatedCharacters ->
                view.showCharacters(updatedCharacters)
            },
            onError = { t -> view.showError(t.message ?: "Failed to unlock") }
        )
    }
    fun useCharacter(characterId: Int , isUsed : Boolean)  {
        tryToExecute(
            callee = {
                charactersRepository.updateCharacterUsedState(characterId , isUsed  )
                charactersRepository.getAllCharacters()
            },
            onSuccess = { updatedCharacters ->
                view.showCharacters(updatedCharacters)
            },
            onError = { t -> view.showError(t.message ?: "Failed to use") }
        )
    }

   var characterPoint = 0
    var characterId = 0
    fun buyCharacter(character: Character) {
        view.showLoading()
        characterPoint=character.price
        characterId=character.id

        tryToExecute(
            callee = { userRepository.getUserStatistics() },
            onSuccess = ::onBuyLifeSuccess,
            onError = {}
        )
    }

    private suspend fun onBuyLifeSuccess(statistics: Triple<Int, Int, Int> ) {
        val (_, points, _) = statistics

        if (points >= characterPoint) {
            userRepository.updatePoints(points - characterPoint)
            val j =userRepository.getUserStatistics().second
            unlockCharacter(characterId , true)
            view.hideLoading()
            onCharacterBoughtSuccessfully()
        } else {
            view.hideLoading()
            view.showError("Not enough points to buy a Character!")
        }
    }  fun onCharacterBoughtSuccessfully() {

    }


}