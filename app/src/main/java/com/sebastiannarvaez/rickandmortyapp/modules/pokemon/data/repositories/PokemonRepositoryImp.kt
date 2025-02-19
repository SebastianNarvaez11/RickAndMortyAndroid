package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.repositories

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sebastiannarvaez.rickandmortyapp.core.network.ApiResponse
import com.sebastiannarvaez.rickandmortyapp.core.network.handleApiError
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.PokemonApiService
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.PokemonPagingSource
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonDetailModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImp @Inject constructor(val api: PokemonApiService) : PokemonRepository {
    override fun getPokemons(): Flow<PagingData<PokemonModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PokemonRepository.MAX_ITEMS_POKEMONS,
                prefetchDistance = PokemonRepository.PREFETCH_ITEMS_POKEMONS
            ),
            pagingSourceFactory = {
                PokemonPagingSource(api)
            }).flow
    }

    override suspend fun getPokemonByName(name: String): ApiResponse<PokemonDetailModel> {
        return runCatching { api.getPokemonByName(name = name) }
            .fold(
                onSuccess = { ApiResponse.Success(it.toDomain()) },
                onFailure = { handleApiError(it as? Exception ?: RuntimeException(it)) }
            )
    }
}