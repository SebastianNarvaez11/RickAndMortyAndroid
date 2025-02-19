package com.sebastiannarvaez.rickandmortyapp.modules.pokemon.data.responses

import com.google.gson.annotations.SerializedName
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.AbilityModel
import com.sebastiannarvaez.rickandmortyapp.modules.pokemon.domain.model.PokemonDetailModel

data class GetPokemonByNameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("abilities") val abilities: List<AbilityResponse>
) {
    fun toDomain(): PokemonDetailModel {
        return PokemonDetailModel(
            id = id,
            name = name,
            abilities = abilities.map {
                AbilityModel(
                    name = it.abilityInfo.name,
                    url = it.abilityInfo.url,
                    isHidden = it.isHidden,
                    slot = it.slot
                )
            }
        )
    }
}

data class AbilityResponse(
    @SerializedName("ability") val abilityInfo: AbilityInfo,
    @SerializedName("is_hidden") val isHidden: Boolean,
    @SerializedName("slot") val slot: Int
)

data class AbilityInfo(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)