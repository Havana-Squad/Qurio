package com.thechance.qurio.domain.repository

import com.thechance.qurio.domain.entity.Character

interface CharactersRepository {
    suspend fun getAllCharacters(): List<Character>
    suspend fun getCharacterById(id: Int): Character?
    suspend fun isCharacterUnlocked(id: Int): Boolean

    suspend fun updateCharacterLockState(id: Int, isUnlocked: Boolean)

    suspend fun updateCharacterUsedState(id: Int, isUsed: Boolean)


}