package com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.data.responses

import com.google.gson.annotations.SerializedName
import com.sebastiannarvaez.rickandmortyapp.modules.rickandmorty.domain.model.CharacterModel

data class ItemCharacterResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("image") val image: String
) {
    fun toDomain(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            image = image,
            isAlive = status == "Alive"
        )
    }
}
