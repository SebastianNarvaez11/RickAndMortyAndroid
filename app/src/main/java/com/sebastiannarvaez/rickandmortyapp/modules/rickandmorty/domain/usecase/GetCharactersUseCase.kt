package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.usecase

import androidx.paging.PagingData
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.model.CharacterModel
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.repository.RickRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: RickRepository
) {
    operator fun invoke(): Flow<PagingData<CharacterModel>> {
        return repository.getAllCharacters()
    }
}