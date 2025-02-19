package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonModel

class PokemonPagingSource(
    private val api: PokemonApiService
) : PagingSource<Int, PokemonModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonModel> {
        val position = params.key ?: 0 // Inicia en la posición 0
        return try {
            // Calcula el offset con base en la posición actual
            val response = api.getPokemons(
                offset = position,
                limit = params.loadSize
            )
            val pokemonList = response.pokemons

            LoadResult.Page(
                data = pokemonList.map { it.toDomain() },
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (pokemonList.isEmpty()) null else position + params.loadSize
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonModel>): Int? {
        return state.anchorPosition
    }
}
