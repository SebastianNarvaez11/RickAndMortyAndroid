package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.usecases

import androidx.paging.PagingData
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.repositories.PokemonRepository
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.model.CharacterModel
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.repository.RickRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<PokemonModel>> {
        return repository.getPokemons()
    }
}