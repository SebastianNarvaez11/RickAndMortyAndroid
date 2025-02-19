package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.presentation.screens.detail

import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonDetailModel

sealed class PokemonDetailState {
    data object Loading : PokemonDetailState()
    data class Error(val error: String) : PokemonDetailState()
    data class Success(val pokemon: PokemonDetailModel) : PokemonDetailState()
}