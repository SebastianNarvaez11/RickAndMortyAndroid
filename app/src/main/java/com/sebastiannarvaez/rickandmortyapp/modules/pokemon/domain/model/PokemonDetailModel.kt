package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model


data class PokemonDetailModel(
    val id: Int,
    val name: String,
    val abilities: List<AbilityModel>
)

data class AbilityModel(
    val name: String,
    val url: String,
    val isHidden: Boolean,
    val slot: Int
)

