package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.usecases

import com.sebastiannarvaez.rickandmortyapp.core.network.ApiResponse
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonDetailModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke(name: String): ApiResponse<PokemonDetailModel> =
        repository.getPokemonByName(name = name)
}