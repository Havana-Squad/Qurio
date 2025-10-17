package com.thechance.qurio.data.repository

import android.content.Context
import com.thechance.qurio.data.local.CharactersDataSource
import com.thechance.qurio.domain.entity.Character
import com.thechance.qurio.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharactersRepositoryImpl(private val context: Context) : CharactersRepository {

    override suspend fun getAllCharacters(): List<Character> = withContext(Dispatchers.IO) {
        CharactersDataSource.getAllCharacters(context)
    }

    override suspend fun getCharacterById(id: Int): Character? = withContext(Dispatchers.IO) {
        CharactersDataSource.getCharacterById(id)
    }

    override suspend fun isCharacterUnlocked(id: Int): Boolean = withContext(Dispatchers.IO) {
        CharactersDataSource.getCharacterById(id)?.isUnlocked ?: false
    }


    override suspend fun updateCharacterLockState(id: Int, isUnlocked :Boolean) = withContext(Dispatchers.IO) {
        CharactersDataSource.updateCharacterLockState(id ,isUnlocked)
    }
    override suspend fun updateCharacterUsedState(id: Int,isUsed :Boolean) = withContext(Dispatchers.IO) {
        CharactersDataSource.updateCharacterUsedState(id,isUsed)
    }

}