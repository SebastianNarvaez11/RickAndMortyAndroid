package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.responses

import com.google.gson.annotations.SerializedName

data class GetPokemonsResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val prev: String?,
    @SerializedName("results") val pokemons: List<ItemPokemonResponse>
)
