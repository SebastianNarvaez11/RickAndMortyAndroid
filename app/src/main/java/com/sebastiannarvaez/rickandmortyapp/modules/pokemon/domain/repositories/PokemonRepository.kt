package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.repositories

import androidx.paging.PagingData
import com.sebastiannarvaez.rickandmortyapp.core.network.ApiResponse
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonDetailModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    companion object {
        const val MAX_ITEMS_POKEMONS = 10
        const val PREFETCH_ITEMS_POKEMONS = 3
    }

    fun getPokemons(): Flow<PagingData<PokemonModel>>

    suspend fun getPokemonByName(name: String): ApiResponse<PokemonDetailModel>
}