package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.responses

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonModel

data class ItemPokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    fun toDomain(): PokemonModel {
        val id = url.trimEnd('/').split("/").lastOrNull()?.toIntOrNull()
        val imageUrl = id?.let {
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$it.png"
        } ?: "https://example.com/placeholder.png"

        return PokemonModel(
            name = name,
            image = imageUrl
        )
    }
}

