package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

interface RickRepository {
    companion object {
        const val MAX_ITEMS = 10
        const val PREFETCH_ITEMS = 3
    }

    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
}